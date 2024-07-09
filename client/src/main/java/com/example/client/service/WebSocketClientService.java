package com.example.client.service;

import com.example.client.entity.MousePosition;
import com.example.client.repository.MousePositionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketClientService extends TextWebSocketHandler {

    private WebSocketSession session;
    private final StandardWebSocketClient client = new StandardWebSocketClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final MousePositionRepository repository;

    public boolean isOpenSession() {
        return session != null && session.isOpen();
    }

    @Autowired
    public WebSocketClientService(MousePositionRepository repository) {
        this.repository = repository;
    }

    public int connect(String serverIp, int serverPort) {
        try {
            String uri = "ws://" + serverIp + ":" + serverPort + "/ws";
            session = client.doHandshake(this, uri).get();
            session.sendMessage(new TextMessage("start"));
        } 
        catch (Exception e) {
            e.fillInStackTrace();
            return -1;
        }
        return 0;
    }
    
    public int disconnect() {
        try {
            if (session != null) {
                session.sendMessage(new TextMessage("stop"));
                session.close();
            }
        } 
        catch (Exception e) {
            e.fillInStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws JsonProcessingException {
        String payload = message.getPayload();
        MousePosition position = objectMapper.readValue(payload, MousePosition.class);

        System.out.println("Принятые координаты: " + position);
        repository.save(position);
    }
}
