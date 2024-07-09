package com.example.server.service;

import com.example.server.MouseStartRequest;
import com.example.server.config.CustomWebSocketServer;
import org.java_websocket.WebSocket;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class MouseService {

    private HashSet<WebSocket> sessionsProccess;

    public MouseService () {
        sessionsProccess = new HashSet<>();
    }

    public void start(WebSocket session, MouseStartRequest request) {
        // Запуск мыши с C++

        int frequency, x, y;

        try {
            frequency = Integer.parseInt(request.getFrequency());
            x = Integer.parseInt(request.getX());
            y = Integer.parseInt(request.getY());
        } catch (Exception e) {
            System.out.println("Не удалось конвертировать входные числа");
            return;
        }

        System.out.println("Запуск мыши");
        sessionsProccess.add(session);
        int duration = 1000 / frequency;

        while (sessionsProccess.contains(session) && session.isOpen()) {

            LocalDateTime dateTime = LocalDateTime.now();
            String message = String.format("save-%s-%s-%s %s %s %s %s %s %s",
                    x,
                    y,
                    dateTime.getYear(),
                    dateTime.getMonthValue(),
                    dateTime.getDayOfMonth(),
                    dateTime.getHour(),
                    dateTime.getMinute(),
                    dateTime.getSecond(),
                    dateTime.getNano());

            try {
                session.send(message);
                System.out.println("Отправлено");
            } catch (Exception e) {
                System.out.println("Не удалось отправить сообщение");
            }
            try {
                Thread.sleep(duration);
            } catch (Exception e) {
                System.out.println("Не удалось сделать задержку");
            }
        }
    }

    public void stop(WebSocket session) {
        // Остановка мыши с C++

        if (!sessionsProccess.contains(session)) {
            System.out.println("Процесс даже не запущен");
            return;
        }
        sessionsProccess.remove(session);
        System.out.println("Остановка мыши");
    }
}
