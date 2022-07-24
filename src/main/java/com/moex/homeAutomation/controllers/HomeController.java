package com.moex.homeAutomation.controllers;

import com.moex.homeAutomation.socket.ServerWebSocket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Set;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String getHome(){
        Set<WebSocketSession> connections = ServerWebSocket.getInstance().getConnections();
        connections.forEach(connection -> {
            try {
                connection.sendMessage(new TextMessage("{\n" +
                        "\t\"name\": \"paco\",\n" +
                        "\t\"edad\": 10\n" +
                        "}"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return "Hello Home";
    }

    @PostMapping("/home/{id}")
    public void setValueDevice(){
    }



}
