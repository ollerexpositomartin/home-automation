package com.moex.homeAutomation.controllers;

import com.moex.homeAutomation.socket.ClientThread;
import com.moex.homeAutomation.socket.Server;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String getHome(){
        List<ClientThread> connections = Server.getInstance(0).getConnections();
        connections.forEach(connection -> connection.sendMessage("Enviado"));
        return "Hello Home";
    }

    @PostMapping("/home/{id}")
    public void setValueDevice(){
    }


}
