package com.advitos.machinedatacsvprinter.utils;

import com.advitos.machinedatacsvprinter.config.HwBinPath;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
@Slf4j
public class HWBinFileFinder extends SimpleFileVisitor<Path> {


    private final HwBinPath filePath;
    private static final String FILE_PATTERN = "glob:*.hwbin";
    private final PathMatcher matcher;
    private final Queue<Path> pathQueue;
    @Setter
    private FileTime compareToDate;

    @Autowired
    public HWBinFileFinder(HwBinPath filePath) {
        this.filePath = filePath;
        this.pathQueue = new ConcurrentLinkedQueue<>();
        matcher = FileSystems.getDefault().getPathMatcher(FILE_PATTERN);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path file, BasicFileAttributes attrs) {

        String name = file.getFileName().toString();

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

        if (filePath.isReadFromCondition()) {
            this.setCompareToDate(FileTime.from(filePath.getReadFrom()));
        }
        else this.setCompareToDate(FileTime.from(Instant.now().minus(filePath.getBackwardDays(), ChronoUnit.DAYS)));



        Path name = file.getFileName();

        if (attrs.isRegularFile() && matcher.matches(name)) {

            String[] timeArray = name.toString().split("_");
            FileTime fileStart = attrs.lastModifiedTime();

            int c2 = fileStart.compareTo(compareToDate);
            if (c2 >= 0) {
//                log.info("FILE TIME: " + fileStart + " | FILE: " + name);
                pathQueue.add(file);
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {

        return super.visitFileFailed(file, exc);
    }

    public Queue<Path> getPathQueue() throws IOException {
        Files.walkFileTree(filePath.getPath(), this);
        log.info("READ FROM: " + compareToDate);
        return pathQueue;
    }
}