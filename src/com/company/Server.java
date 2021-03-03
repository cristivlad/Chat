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
            System.out.println("Waiting for connection...");
            Socket client = incoming.accept();
            System.out.println("Client connected");

            BufferedReader incomingServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter outgoingServer = new PrintWriter(client.getOutputStream(),true);

            String date = (new Date()).toString();
            outgoingServer.println(date);
            System.out.println("Server sent " + date);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }



    }
}
