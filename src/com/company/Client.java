package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost",7777)) {
            BufferedReader inputClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputClient = new PrintWriter(socket.getOutputStream(),true);

            String serverResponse = inputClient.readLine();
            System.out.println(serverResponse);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
