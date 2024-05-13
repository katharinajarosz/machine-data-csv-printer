package com.advitos.machinedatacsvprinter.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Component
public class HwBinPath {

    @Getter
    private final Path path;
    @Getter
    private final Path csvPath;
    @Getter
    private final Instant readFrom;
    @Getter
    private final int backwardDays;
    @Getter
    private final boolean readFromCondition;

    @Autowired
    public HwBinPath(@Value("${mdImportService.path}") String pathStr,
                     @Value("${mdImportService.path.csv}") String csvPathStr,
                     @Value("#{T(java.time.Instant).parse('${mdImportService.read_from_date}')}") Instant readFrom,
                     @Value("${mdImportService.backward_days}") int backwardDays,
                     @Value("${mdImportService.read_from_condition}") boolean readFromCondition) {
        this.path = Paths.get(pathStr).toAbsolutePath().normalize();
        this.csvPath = Paths.get(csvPathStr).toAbsolutePath().normalize();
        this.readFrom = readFrom;
        this.backwardDays = backwardDays;
        this.readFromCondition = readFromCondition;
    }
}



