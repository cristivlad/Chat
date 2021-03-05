package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket incoming = new ServerSocket(7777) ) {
            while(true){
                System.out.println("Waiting for connection...");
                Socket client = incoming.accept();

                BufferedReader incomingServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter outgoingServer = new PrintWriter(client.getOutputStream(),true);

                ClientHandler t = new ClientHandler(client,incomingServer,outgoingServer);
                t.start();

                String date = (new Date()).toString();
                System.out.println("Client " + incomingServer.readLine() + " connected on " + date);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }



    }
}
