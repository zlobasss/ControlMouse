package com.example.client.controller;

import com.example.client.config.Config;
import com.example.client.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
