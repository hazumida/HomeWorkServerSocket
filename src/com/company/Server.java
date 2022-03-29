package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        String answer;

        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket clientSocket = serverSocket.accept();
             PrintWriter out =new PrintWriter(clientSocket.getOutputStream(),true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            System.out.println("New connection accepted");
            final String name = in.readLine();
            out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
            out.flush();
            System.out.println(name + " " + clientSocket.getPort());

            out.println("Write your name");
            String nickname = in.readLine();
            out.println("Are you child? (yes/no)");

            answer = in.readLine();
            while (true) {
                if (answer.equals("yes")) {
                    out.println("ok");
                    out.println("Welcome to the kids area, " + nickname + "! Let's play!");
                    break;
                }
                if (answer.equals("no")) {
                    out.println("ok");
                    out.println("Welcome to the adult zone, " + nickname + "! Have a good rest, or a good working day!");
                    break;
                }
                out.println("Are you child? (yes/no)");
                answer = in.readLine();

            }
        } catch (IOException err){
            err.printStackTrace();
        }
    }
}
