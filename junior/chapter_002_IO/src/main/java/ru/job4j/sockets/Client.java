package ru.job4j.sockets;

import java.io.*;
import java.net.*;

public class Client {

    private Socket socket;
    private BufferedReader console;

    public Client(Socket socket, BufferedReader console) {
        this.socket = socket;
        this.console = console;
    }

    public void connect() throws IOException {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String word;
            do {
                word = this.console.readLine();
                out.println(word);
                String str;
                while (true) {
                    str = in.readLine();
                    if (str.isEmpty()) {
                        break;
                    }
                    System.out.println(str);
                }
            } while (!word.toLowerCase().contains("пока"));
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName("localhost"), 5000);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            Client user = new Client(socket, console);
            user.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
