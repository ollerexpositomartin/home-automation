package com.moex.homeAutomation;

import com.moex.homeAutomation.socket.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class HomeAutomationApplication {

	public static void main(String[] args) throws IOException {
		Server socketServer = Server.getInstance(Integer.parseInt(System.getenv("PORT")));
		socketServer.startServer();

	SpringApplication.run(HomeAutomationApplication.class, args);
	}

}
