package com.example.server.controller;

import com.example.server.service.ControlService;
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
    public ResponseEntity<?> startListening() {
        controlService.startListening();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/v1/stop")
    public ResponseEntity<?> stopListening() {
        controlService.stopListening();
        return ResponseEntity.ok().build();
    }
}
