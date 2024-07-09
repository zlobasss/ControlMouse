package com.example.server.service;

import org.springframework.stereotype.Service;

@Service
public class MouseService {

    public void start() {
        // Запуск мыши с C++
        System.out.println("Запуск мыши");
    }

    public void stop() {
        // Остановка мыши с C++
        System.out.println("Остановка мыши");
    }
}
