package com.moex.homeAutomation.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ServerWebSocket extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(ServerWebSocket.class);
    private static ServerWebSocket serverSession;

    private final Set<WebSocketSession> connections = new CopyOnWriteArraySet<>();

    private ServerWebSocket() {}

    public static ServerWebSocket getInstance() {
        if (serverSession == null)
            return serverSession = new ServerWebSocket();
        return serverSession;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Client connected "+ session.getRemoteAddress());
        connections.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        logger.info("client disconnected "+session.getRemoteAddress());
        connections.remove(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String request = message.getPayload();
        logger.info("Server received: {}", request);

        String response = String.format("response from server to '%s'", HtmlUtils.htmlEscape(request));
        logger.info("Server sends: {}", response);
        session.sendMessage(new TextMessage(response));
    }

    public Set<WebSocketSession> getConnections() {
        return connections;
    }
}
