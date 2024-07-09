package com.example.client.controller;

import com.example.client.service.ControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
        try {
            if (!controlService.startServer()) {
                throw new ServerStartException();
            }
        } catch (ServerStartException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Server started", HttpStatus.OK);
    }

    @PostMapping("/v1/stop")
    public ResponseEntity<?> stopServer() {
        try {
            if (!controlService.stopServer()) {
                throw new ServerStopException();
            }
        } catch (ServerStartException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Server stopped", HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class ServerStartException extends RuntimeException {
        public ServerStartException() {
            super("Server couldn't be started");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class ServerStopException extends RuntimeException {
        public ServerStopException() {
            super("Server couldn't be stopped");
        }
    }
}
