package com.example.client.service;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;

@Component
public class WebSocketClientService extends TextWebSocketHandler {

    private WebSocketSession session;
    private final StandardWebSocketClient client = new StandardWebSocketClient();

    public void connect(String serverIp, int serverPort) {
        try {
            String uri = "ws://" + serverIp + ":" + serverPort + "/ws";
            this.session = client.doHandshake(this, uri).get();
        } 
        catch (Exception e) {
            e.fillInStackTrace();
        }
    }
    
    public void disconnect() {
        try {
            if (session != null) {
                session.close();
            }
        } 
        catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        // Обработка сообщения о координатах мыши и сохранение их в БД.
    }
}
