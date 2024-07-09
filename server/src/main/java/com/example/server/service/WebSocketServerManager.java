package com.example.server.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/ws")
public class WebSocketServerService {

    private Session session;
    private static final CopyOnWriteArraySet<WebSocketServerService> webSocketSet = new CopyOnWriteArraySet<>();

    @PostConstruct
    public void init() {
        System.out.println("WebSocket сервер инициализирован");
    }

    public void start(int port) {
        // Запуск WS сервера

        System.out.println("Запуск WS: " + port);
    }

    public void stop() {
        // Остановка WS сервера

        System.out.println("Остановка WS");
    }

    @PreDestroy
    public void destroy() {
        stop();
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        System.out.println("Новое соединение: " + session.getId());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        System.out.println("Закрыто соединение: " + this.session.getId());
    }

    @OnMessage
    public void onMessage(String message) {
        // Обработка сообщения клиента

        System.out.println("Сообщение: " + message);
    }
}
