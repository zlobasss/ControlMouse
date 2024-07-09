package com.example.server.service;

import com.example.server.config.CustomWebSocketServer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Service
public class WebSocketServerManager {

    private ScheduledExecutorService serverExecutor;
    private CustomWebSocketServer server;

    @PostConstruct
    public void init() {
        serverExecutor = Executors.newSingleThreadScheduledExecutor();
        System.out.println("WebSocket сервер инициализирован");
    }

    public void start(int port) {
        // Запуск WS сервера
        server = new CustomWebSocketServer(new InetSocketAddress(port));
        serverExecutor.submit(() -> {
           try {
               server.start();
               System.out.println("Запуск WS: " + port);
           } catch (Exception e) {
               e.fillInStackTrace();
           }
        });
    }

    public void stop() {
        // Остановка WS сервера
        if (server != null) {
            try {
                server.stop();
                System.out.println("Остановка WS сервера");
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
    }

    @PreDestroy
    public void destroy() {
        stop();
        serverExecutor.shutdown();
    }
}
