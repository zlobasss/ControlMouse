package com.example.server.service;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/ws")
public class  {

    private static final CopyOnWriteArraySet<WebSocketServerService> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        System.out.println("New connection: " + session.getId());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        System.out.println("Connection closed: " + this.session.getId());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Message: " + message);
        broadcastMessage(message);
    }

    private void broadcastMessage(String message) {
        for (WebSocketServerService webSocketServerService : webSocketSet) {
            try {
                webSocketServerService.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.fillInStackTrace();
            }
        }
    }
}
