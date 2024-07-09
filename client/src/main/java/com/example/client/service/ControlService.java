package com.example.client.service;

import com.example.client.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;

@Service
public class ControlService {

    private final ConfigService configService;
    private final WebSocketClientService webSocketClient;

    @Autowired
    public ControlService(ConfigService configService, WebSocketClientService webSocketClient) {
        this.configService = configService;
        this.webSocketClient = webSocketClient;
    }

    public void startServer() {
        Config config = configService.getConfig();
        webSocketClient.connect(config.getServerIp(), config.getServerPort());
    }

    public void stopServer() {
        webSocketClient.disconnect();
    }
}
