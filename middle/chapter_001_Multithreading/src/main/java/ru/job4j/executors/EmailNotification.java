package ru.job4j.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Простейший сервис рассылки оповещений.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class EmailNotification {

    private final ExecutorService pool;

    public EmailNotification() {
        this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    /**
     * Создает и выполняет задачу - отправка оповещения заданному пользователю.
     * @param user объект с данными пользователя.
     */
    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = String.format("Notification %s to email %s", user.username, user.email);
            String body = String.format("Add a new event to %s", user.username);
            this.send(subject, body, user.email);
        });
    }

    /**
     * Отравляет сообщение.
     * @param subject тема сообщения.
     * @param body тело письма.
     * @param email адрес получателя.
     */
    public void send(String subject, String body, String email) {
    }

    /**
     * Закрывает все потоки сервиса.
     */
    public void close() {
        this.pool.shutdown();
    }

    public ExecutorService getPool() {
        return pool;
    }

    /**
     * Класс пользователя.
     */
    public static class User {
        private String username;
        private String email;

        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }
    }
}
