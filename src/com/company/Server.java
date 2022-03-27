package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket; // порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
        PrintWriter out = null;
        BufferedReader in = null;
        Socket clientSocket = null;
        String answer;
        try {
            serverSocket = new ServerSocket(8080);
            clientSocket = serverSocket.accept(); // ждем подключения
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
