package com.example.client.controller;

import com.example.client.service.ControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControlController {

    private final ControlService controlService;

    @Autowired
    public ControlController(ControlService controlService) {
        this.controlService = controlService;
    }

    @PostMapping("/v1/start")
    public ResponseEntity<?> startServer() {
        controlService.startServer();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/v1/stop")
    public ResponseEntity<?> stopServer() {
        controlService.stopServer();
        return ResponseEntity.ok().build();
    }
}
