package com.readapp.backend.managers;

import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

public class SocketManager {
    private static ConcurrentHashMap<String, WebSocketSession> manager = new ConcurrentHashMap<String, WebSocketSession>();

    public static void add(String key, WebSocketSession webSocketSession) {
        System.out.println("New Connection!");
        manager.put(key, webSocketSession);
    }

    public static void remove(String key) {
        manager.remove(key);
    }

    public static WebSocketSession get(String key) {
        return manager.get(key);
    }

}
