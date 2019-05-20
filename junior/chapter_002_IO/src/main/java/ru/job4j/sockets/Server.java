package ru.job4j.sockets;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;

public class Server {

    private Map<String, String[]> answers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private Socket socket;

    public Server(Socket socket) throws IOException {
        List<String> buf = new ArrayList<>();
        this.socket = socket;
        try (BufferedReader source = new BufferedReader(new FileReader(getClass().getClassLoader().getResource("bot.txt").getFile()))) {
            source.lines().forEach(str -> buf.add(str));
            for (String line : buf) {
                String[] tmp = line.split("-");
                answers.putAll(Arrays.stream(tmp[0].split(":"))
                        .collect(Collectors.toMap(elem -> elem, elem -> tmp[1].split("\\\\"))));
            }
        }
    }

    private String[] answer(String question) {
        String[] res = {"Не понял вопроса..."};
        return this.answers.computeIfAbsent(question, key -> res);
    }

    public void connect() throws IOException {
        try (
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String ask;
            do {
                System.out.println("Ожидаю вопроса...");
                ask = in.readLine();
                System.out.println(ask);
                Arrays.stream(this.answer(ask)).forEach(out::println);
                out.println();
            } while (!ask.toLowerCase().contains("пока"));
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new ServerSocket(5000).accept()) {
            Server server = new Server(socket);
            server.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
