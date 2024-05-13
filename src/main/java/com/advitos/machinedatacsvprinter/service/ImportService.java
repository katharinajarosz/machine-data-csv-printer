package com.advitos.machinedatacsvprinter.service;


import com.advitos.machinedatacsvprinter.config.HwBinPath;
import com.advitos.machinedatacsvprinter.model.CsvHeaderEnum;
import com.advitos.machinedatacsvprinter.model.SWVersion;
import com.advitos.machinedatacsvprinter.model.beans.*;
import com.advitos.machinedatacsvprinter.utils.HWBinFileFinder;
import com.advitos.machinedatacsvprinter.utils.ServiceUtils;
import com.advitos.machinedatacsvprinter.utils.TimestampConverter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static org.apache.commons.io.FileUtils.*;

@RequiredArgsConstructor
@Service
@EnableScheduling
@Slf4j
public class ImportService {

    private static final int HEADER_SIZE = 1024;
    private Queue<Path> pathQueue;
    byte[] intByteArr = new byte[4];

    // Constructor fields
    private final HwBinPath filePath;
    private final TimestampConverter timestampConverter;
    private final ServiceUtils serviceUtils;

    @PostConstruct
    private void init() {
        scanFileSystem();
    }

//    @Scheduled(fixedDelayString = "PT3H")
    private void scanFileSystem() {

        try {
            HWBinFileFinder hwBinFileFinder = new HWBinFileFinder(filePath);
            Instant start1 = Instant.now();
            pathQueue = hwBinFileFinder.getPathQueue();
            log.info("PATH QUEUE SIZE: " + pathQueue.size());
            Instant start2 = Instant.now();
            Duration dif1 = Duration.between(start1, start2);
//            log.info("WALK FILE TREE DURATION: " + dif1.getSeconds() + " S");

            MachineDataImportService machineDataImportService = new MachineDataImportService();
            while (machineDataImportService.getProcessImage()) ;
            Instant end = Instant.now();
            Duration dif2 = Duration.between(start2, end);
//            log.info("DATE IMPORT DURATION: " + dif2.getSeconds() + " S");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public class MachineDataImportService {


        public boolean getProcessImage() throws Exception {
            boolean result = false;

            if (pathQueue.peek() != null) {
                Path path = pathQueue.poll();
                long fileSize = FileUtils.sizeOf(path.toFile());
                if (fileSize != 0) {
                    BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(path.toFile()));
                    try (inputStream) {

                        String fileName = path.getFileName().toString();
                        String uniqueFileString = fileName.substring(0, 30);

                        insertMachineRun(inputStream, path);

                    } catch (IOException | IndexOutOfBoundsException | NullPointerException | DateTimeException e) {

                        log.error("GET PROCESS IMAGE: " + e.getClass() + ": " + e.getMessage());
                        log.info("EXCEPTION FOR FILE: " + path.getFileName() +
                                " | FILE SIZE: " + byteCountToDisplaySize(FileUtils.sizeOf(path.toFile())));

                        inputStream.close();
                    }

                }

                result = true;
            }
            return result;
        }

        private void insertMachineRun(BufferedInputStream inputStream, Path path) throws Exception {

            String fileName = path.getFileName().toString();
            String machineNumber = fileName.substring(20, 30);
            String machineId = fileName.substring(27, 30);

            Long lastTimestamp = 0l;

            SWVersion version = serviceUtils.matchVersionEnum(fileName);

            if (serviceUtils.matchFileVersion(fileName)) {

                byte[] buff = new byte[HEADER_SIZE];
                inputStream.read(buff, 0, HEADER_SIZE);

            }

            List<String[]> records = new ArrayList<>();

            // machine data frame
            int frameCounter = 0;
            int readBytes;
            while ((readBytes = inputStream.read(intByteArr)) != -1) {

                byte[] b = new byte[1];

                int fromByteArray = Unpooled.wrappedBuffer(intByteArr).getInt(0);

                if (fromByteArray > 0) {

                    byte[] data = new byte[fromByteArray];
                    inputStream.read(data);

                    ByteBuf nettyBuffer = Unpooled.wrappedBuffer(data);

                    ProcessImage processImage = new ProcessImage();
                    processImage.parse(nettyBuffer, version);

                    inputStream.read(b);

                    handleProcessImage(processImage, records, machineId);

                    lastTimestamp = processImage.getIoData().getTimestamp1();

                    frameCounter++;

                }
            }

            if (frameCounter > 0) {

                log.info("RECORD LIST SIZE: " + records.size());
                if (records.size() > 1) {
//                    log.info("FIRST RECORD: " + Arrays.toString(records.get(0)));
//                    log.info("LAST RECORD: " + Arrays.toString(records.get(records.size() - 1)));
                    String fileBase = FilenameUtils.getBaseName(fileName);
                    Path csvPath = Paths.get(filePath.getCsvPath().toString(), fileBase + ".csv");
                    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                            .setHeader(CsvHeaderEnum.class)
                            .setSkipHeaderRecord(false)
//                        .setDelimiter(";")
                            .build();
                    try (BufferedWriter bufferedWriter = Files.newBufferedWriter(csvPath, StandardCharsets.UTF_8);
                         CSVPrinter csvPrinter = new CSVPrinter(bufferedWriter, csvFormat)) {
                        for (String[] rowRecord : records) {
                            csvPrinter.printRecord(rowRecord);
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }

            }

//            log.info("FILE SIZE: " + FileUtils.byteCountToDisplaySize(FileUtils.sizeOf(path.toFile())) + " | FRAME COUNTER: " + frameCounter);
            log.info("CSV FOR FILE: " + fileName + " SAVED!");

        }

        private void handleProcessImage(ProcessImage processImage, List<String[]> records, String machineId) {
            List<String> rowRecordList = new ArrayList<>();

//            rowRecordList.add(timestampConverter.convertToUtcInstant(processImage.getIoData().getTimestamp1()).toString());
            rowRecordList.add(timestampConverter.convertToLocalDateTime(processImage.getIoData().getTimestamp1()));
            rowRecordList.add(machineId);
            rowRecordList.add(getSystemMainState(processImage.getProcessControlData().getSystemState()));

            for (NumericInput numericInput : processImage.getNumericInputs()) {
                if (numericInput.getType() > 6 && numericInput.getType() < 94) {
                    rowRecordList.add(String.valueOf(numericInput.getCd()));
                }
            }

            for (DigitalInput digitalInput : processImage.getDigitalInputs()) {
                if (digitalInput.getType() > 1 && digitalInput.getType() < 24) {
                    rowRecordList.add(String.valueOf(digitalInput.isCd()));
                }
            }

            for (ClosedLoop closedLoop : processImage.getClosedLoops()) {
                if (closedLoop.getType() < 27) {
                    rowRecordList.add(String.valueOf(closedLoop.getHmi()));
                    rowRecordList.add(String.valueOf(closedLoop.getDem()));
                }

            }

            for (NumericOutput numericOutput : processImage.getNumericOutputs()) {
                if (numericOutput.getType() < 13) {
                    rowRecordList.add(String.valueOf(numericOutput.getCd()));
                }
            }

            for (DigitalOutput digitalOutput : processImage.getDigitalOutputs()) {
                if (digitalOutput.getType() > 1 && digitalOutput.getType() < 8) {
                    rowRecordList.add(String.valueOf(digitalOutput.isHmi()));
                }
            }

            String[] rowRecord = rowRecordList.toArray(String[]::new);
            if (rowRecord.length < 184) {
                log.info("XXX: " + rowRecord.length);
            }
            records.add(rowRecord);

        }

        private int getStateValue(int mainState, ProcessImage processImage) {

            int stateValue = 0;
            switch (mainState) {
                case 0 -> stateValue = processImage.getProcessControlData().getStartupState();
                case 1 -> stateValue = processImage.getProcessControlData().getIdleState();
                case 2 -> stateValue = processImage.getProcessControlData().getServiceState();
                case 3 -> stateValue = processImage.getProcessControlData().getPreparationState();
                case 4 -> stateValue = processImage.getProcessControlData().getTreatmentState();
                case 5 -> stateValue = processImage.getProcessControlData().getPostprocessState();
                case 6 -> stateValue = processImage.getProcessControlData().getDesinfectionState();
                default -> {
                }
            }

            return stateValue;
        }

        private String getSystemMainState(int mainState) {

            String systemMainState = "";
            switch (mainState) {
                case 0 -> systemMainState = "Startup";
                case 1 -> systemMainState = "Idle";
                case 2 -> systemMainState = "Service";
                case 3 -> systemMainState = "Preparation";
                case 4 -> systemMainState = "Treatment";
                case 5 -> systemMainState = "Postprocess";
                case 6 -> systemMainState = "Disinfection";
            }

            return systemMainState;
        }


    }
}
