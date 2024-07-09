package com.example.server.service;

import jakarta.websocket.OnMessage;

import java.net.ServerSocket;

public class WebSocketServerService {

    private ServerSocket serverSocket;

    public void start(int port) {
        // Запуск WS сервера
    }

    public void stop() {
        // Остановка WS сервера
    }

    @OnMessage
    public void onMessage(String message) {
        // Обработка сообщения клиента
    }
}
