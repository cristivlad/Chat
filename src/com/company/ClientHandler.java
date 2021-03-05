package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread{

    private final Socket s;
    private final BufferedReader incoming;
    private final PrintWriter outgoing;

    public ClientHandler(Socket s, BufferedReader incoming, PrintWriter outgoing) {
        this.s = s;
        this.incoming = incoming;
        this.outgoing = outgoing;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String incomingMessage = incoming.readLine();
                outgoing.println("Message " + incomingMessage + " received!");

                if (incomingMessage.equalsIgnoreCase("disconnected")) {
                    break;
                }


            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
