package com.example.client.controller;

import com.example.client.config.Config;
import com.example.client.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ConfigController {

    private final ConfigService configService;

    @Autowired
    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @PostMapping("/v1/config")
    public ResponseEntity<?> setConfig(@RequestBody Config config) {
        configService.setConfig(config);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/config")
    public ResponseEntity<Config> getLogConfig() {
        return new ResponseEntity<>(configService.getConfig(), HttpStatus.OK);
    }
}
