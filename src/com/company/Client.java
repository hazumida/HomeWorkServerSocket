package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String answer;
        String host = "netology.homework";
        int port = 8080;
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            out.println("GET / HTTP/1.1");
            out.flush();
            String resp = in.readLine();
            System.out.println(resp);

            resp = in.readLine();
            System.out.println(resp);
            String nickname = scanner.nextLine();
            out.println(nickname);
            resp = in.readLine();
            System.out.println(resp);

            answer = scanner.nextLine();
            out.println(answer);

            while (true) {
                resp = in.readLine();

                if (resp.equals("ok")) {
                    break;
                }

                System.out.println(resp);
                answer = scanner.nextLine();
                out.println(answer);

            }
            resp = in.readLine();
            System.out.println(resp);

        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
