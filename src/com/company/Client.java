package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost",7777)) {
            BufferedReader inputClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputClient = new PrintWriter(socket.getOutputStream(),true);

            System.out.println("Your name is: ");
            Scanner sc = new Scanner(System.in);
            String clientName = sc.nextLine();
            outputClient.println(clientName);

            while(true) {
                System.out.println("Write a message");
                String message = sc.nextLine();

                if(message.equalsIgnoreCase("exit")) {
                    outputClient.println("disconnected");
                    break;
                }
                outputClient.println(message);

                String serverResponse = inputClient.readLine();
                System.out.println(serverResponse);


            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
