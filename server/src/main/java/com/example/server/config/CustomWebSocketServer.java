package com.example.server.config;
import com.example.server.service.MouseService;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.web.socket.WebSocketSession;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CustomWebSocketServer extends WebSocketServer {

    private static final Set<CustomWebSocketServer> clients = Collections.synchronizedSet(new HashSet<>());
    private final MouseService mouseService;
    private volatile boolean running = false;

    public CustomWebSocketServer(InetSocketAddress address) {
        super(address);
        mouseService = new MouseService();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        clients.add(this);
        System.out.println("Новое соединение: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        clients.remove(this);
        System.out.println("Закрыто соединение: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {

        System.out.println("Сообщение: " + message);
        if ("start".equalsIgnoreCase(message)) {
            running = true;
            new Thread(() -> {
                mouseService.start(conn);
            }).start();
        } else if ("stop".equalsIgnoreCase(message)) {
            running = false;
            conn.send("Stopped.");
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.fillInStackTrace();
        if (conn != null) {
            System.err.println("Ошибка соединения: " + conn.getRemoteSocketAddress());
        }
    }

    @Override
    public void onStart() {
        System.out.println("Сервер WS запущен!");
    }
}
