package com.example.client.controller;

import com.example.client.config.LogConfig;
import com.example.client.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("/v1/log")
    public ResponseEntity<?> setLogConfig(@RequestBody LogConfig logConfig) {
        logService.setLogConfig(logConfig);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/log")
    public ResponseEntity<LogConfig> getLogConfig() {
        try {
            LocalDateTime dateTime = LocalDateTime.of(2024, 7 , 9, 18, 16, 40, 352578504);
            System.out.println("Отработало");
            Timestamp.valueOf(dateTime);
            System.out.println("Отработало");
        } catch (DateTimeException e) {
            System.out.println("Не отработало");
        }
        LocalDateTime dateTime = LocalDateTime.of(2024, 7 , 9, 18, 16, 40, 352578504);
        return new ResponseEntity<>(logService.getLogConfig(), HttpStatus.OK);
    }
}
