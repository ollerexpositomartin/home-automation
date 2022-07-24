package com.moex.homeAutomation.controllers;

import com.google.gson.Gson;
import com.moex.homeAutomation.domain.models.ActionMessage;
import com.moex.homeAutomation.domain.models.ActionMessage.States;
import com.moex.homeAutomation.socket.ServerWebSocket;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;
import java.util.Set;

@RestController
public class HomeController {

    @GetMapping("/actionON")
    @RequestMapping(produces = "text/plain;charset=UTF-8")
    public String executeActionON(@RequestParam String id){
        Set<WebSocketSession> connections = ServerWebSocket.getInstance().getConnections();

        ActionMessage action = new ActionMessage(id,States.ON.name());
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

    @GetMapping("/actionOFF/{id}")
    public String executeActionOFF(@PathVariable String id){
        Set<WebSocketSession> connections = ServerWebSocket.getInstance().getConnections();

        ActionMessage action = new ActionMessage(id,States.OFF.name());
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

    @GetMapping("/actionANALOGIC")
    public String executeActionAnalogic(){
        return "ANALOGIC";
    }


    @GetMapping("/devices")
    public void getDevices(){
    }

    @PostMapping("/devices")
    public void registerDevice(){
    }



}
