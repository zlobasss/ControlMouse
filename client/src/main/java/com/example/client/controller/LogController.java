package com.example.client.controller;

import com.example.client.config.LogConfig;
import com.example.client.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
