package com.moex.homeAutomation.controllers;

import com.google.gson.Gson;
import com.moex.homeAutomation.domain.models.ActionMessage;
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

    @GetMapping("/action")
    public String executeAction(){
        Set<WebSocketSession> connections = ServerWebSocket.getInstance().getConnections();

        ActionMessage action = new ActionMessage("LAMP","ON");
        String json = new Gson().toJson(action);

        connections.forEach(connection -> {
            try {
                connection.sendMessage(new TextMessage(json));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return "Hello Home";
    }

    @GetMapping("/devices")
    public void getDevices(){
    }

    @PostMapping("/devices")
    public void registerDevice(){
    }



}
