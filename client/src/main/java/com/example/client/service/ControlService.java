package com.example.client.service;

import com.example.client.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControlService {

    private final ConfigService configService;
    private final WebSocketClientService webSocketClient;

    @Autowired
    public ControlService(ConfigService configService, WebSocketClientService webSocketClient) {
        this.configService = configService;
        this.webSocketClient = webSocketClient;
    }

    public boolean startServer() {
        Config config = configService.getConfig();
        if (config == null) {
            return false;
        }
        return webSocketClient.connect(config.getServerIp(), config.getServerPort()) == 0;
    }

    public boolean stopServer() {
        return webSocketClient.disconnect() == 0;
    }
}
