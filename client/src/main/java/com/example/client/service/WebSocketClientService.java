package com.example.client.service;

import com.example.client.request.MousePositionRequest;
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
    private final MouseService service;

    public boolean isOpenSession() {
        return session != null && session.isOpen();
    }

    @Autowired
    public WebSocketClientService(MouseService service) {
        this.service = service;
    }

    public int connect(String serverIp, int serverPort) {
        if (isOpenSession()) {
            return -41;
        }
        String uri = "ws://" + serverIp + ":" + serverPort + "/ws";
        try {
            session = client.doHandshake(this, uri).get();
        } 
        catch (Exception e) {
            return -42;
        }
        return 0;
    }
    
    public int disconnect() {
        if (!isOpenSession()) {
            return -43;
        }
        try {
            session.close();
        }
        catch (Exception e) {
            return -44;
        }
        return 0;
    }

    public int sendMessage(String message) {
        try {
            session.sendMessage(new TextMessage(message));
        } catch (Exception e) {
            return -45;
        }
        return 0;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws JsonProcessingException {
        String payload = message.getPayload();
        String[] data = payload.split("-");
        int response = -1;
        switch (data[0]) {
            case "save":
                if (data.length != 4) {
                    System.out.println("Неверный формат запроса от сервера");
                    System.out.println(data.length);
                    System.out.println(payload);
                    return;
                }
                MousePositionRequest request = new MousePositionRequest(data[1], data[2], data[3]);
                response = service.save(request);
                break;
        }
        if (response != 0) {
            System.out.println("Запрос не выполнен");
            return;
        }
        System.out.println("Запрос выполнен");
    }
}
