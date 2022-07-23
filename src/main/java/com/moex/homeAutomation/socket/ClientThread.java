package com.moex.homeAutomation.socket;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {

    protected Socket socket;
    private InputStream inp;
    private BufferedReader bfr;
    private DataOutputStream out;


    public ClientThread(Socket socket){
        this.socket = socket;
        start();
    }

    @Override
    public void run() {
        try {
            inp = socket.getInputStream();
            bfr = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());

        }catch (IOException e){
            return;
        }
        String line;

        try {
            while (true){
                line = bfr.readLine();
                System.out.println(line);
            }
        }catch (IOException ioex){
            ioex.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        if(out!=null){
            PrintWriter writer = new PrintWriter(out,true);
            writer.println(message);
        }
    }
}
