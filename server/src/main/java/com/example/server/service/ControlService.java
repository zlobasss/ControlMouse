package com.example.server.service;

import com.example.server.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControlService {

    private final ConfigService configService;
    private final WebSocketServerManager webSocketServerManager;

    @Autowired
    public ControlService(ConfigService configService, WebSocketServerManager webSocketServerManager, MouseService mouseService) {
        this.configService = configService;
        this.webSocketServerManager = webSocketServerManager;
    }

    public void startListening() {
        Config config = configService.getConfig();
        webSocketServerManager.start(config.getPort());
    }

    public void stopListening() {
        webSocketServerManager.stop();
    }
}
