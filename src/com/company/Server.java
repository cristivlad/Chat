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
            int connections = 0;
            System.out.println("Waiting for connection...");
            Socket client = incoming.accept();
            connections++;

            BufferedReader incomingServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter outgoingServer = new PrintWriter(client.getOutputStream(),true);

            String date = (new Date()).toString();
            System.out.println("Client " + incomingServer.readLine() + " connected on " + date);

            while (true) {
                String incomingMessage = incomingServer.readLine();
                outgoingServer.println("Message " + incomingMessage + " received!");

                if (incomingMessage.equalsIgnoreCase("disconnected")) {
                    connections--;
                    if(connections == 0) {
                        System.out.println("no connections opened - closing in 2 seconds");
                        try {
                            Thread.sleep(2000);
                        }catch (InterruptedException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }

                }
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }



    }
}
