package com.example.templateservice.manager;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FilenameManager {

    private static final String DATE_PATTERN = "yyyy-MM-dd_HH-mm-ss-SSS";

    private final DateTimeFormatter formatter;

    public String generate() {
        return "report_" + formatter.format(LocalDateTime.now());
    }

    public String generate(String baseName) {
        if (baseName == null || baseName.isBlank()) return generate();
        return baseName + "_" + formatter.format(LocalDateTime.now());
    }

    public String generate(String baseName, String extension) {
        if (baseName == null || baseName.isBlank()) return generate();
        if(extension == null || extension.isBlank()) return generate(baseName);
        return baseName + "_" + formatter.format(LocalDateTime.now()) + extension;
    }

    public FilenameManager() {
        formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
    }
}
