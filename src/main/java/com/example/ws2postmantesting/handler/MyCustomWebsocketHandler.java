package com.example.ws2postmantesting.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.*;

public class MyCustomWebsocketHandler implements WebSocketHandler {

    private static final Logger log = LogManager.getLogger(MyCustomWebsocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connected, session id: {}", session.getId());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = (String) message.getPayload();

        log.info("Received message payload: {}", payload);

        session.sendMessage(new TextMessage("Starting processing for session: " + session + " ; payload:  " + payload));
        Thread.sleep(1000);
        session.sendMessage(new TextMessage("Ending processing for session: " + session + " ; payload:  " + payload));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("Transport error exception: {} on session {}", exception.getMessage(), session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("Connection closed for session: {} with status code {}", session.getId(), closeStatus.getCode());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
