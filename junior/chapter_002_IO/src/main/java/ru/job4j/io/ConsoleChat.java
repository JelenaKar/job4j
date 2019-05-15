package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {

    private List<String> buffer;
    private BufferedReader user;

    private String address = new File(getClass().getClassLoader().getResource("chat.txt").getFile()).getParent();

    public String getAddress() {
        return this.address;
    }

    private List<String> load() throws IOException {
        List<String> buf = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(this.address + "/chat.txt"))) {
            read.lines().forEach(str -> buf.add(str));
        }
        return buf;
    }

    public List<String> getBuffer() {
        return this.buffer;
    }

    private void writeAll(List<String> cash, String target) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            for (String line : cash) {
                writer.write(line + "\n");
            }
        }
    }

    public ConsoleChat(BufferedReader user) throws IOException {
        this.buffer = this.load();
        this.user = user;
    }

    public void start() throws IOException {
        List<String> log = new LinkedList<>();
        Random rand = new Random();
        String req, resp;
        boolean mute = false;
        do {
            System.out.print("Ты: ");
            req = this.user.readLine();
            log.add("Ты: " + req);
            if ("стоп".equals(req)) {
                mute = true;
            } else if ("продолжить".equals(req)) {
                mute = false;
            }
            if (!mute && !"закончить".equals(req)) {
                resp = this.buffer.get(rand.nextInt(this.buffer.size()));
                log.add("Бот: " + resp);
                System.out.printf("Бот: %s\n", resp);
            }
        } while (!"закончить".equals(req));
        this.writeAll(log, this.address + "/chat.log");
    }

    public static void main(String[] args) {
        try (BufferedReader user = new BufferedReader(new InputStreamReader(System.in))) {
            ConsoleChat chat = new ConsoleChat(user);
            chat.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}