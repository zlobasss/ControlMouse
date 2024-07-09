package com.example.server.config;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CustomWebSocketServer extends WebSocketServer {

    private static final Set<CustomWebSocketServer> clients = Collections.synchronizedSet(new HashSet<>());

    public CustomWebSocketServer(InetSocketAddress address) {
        super(address);
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
