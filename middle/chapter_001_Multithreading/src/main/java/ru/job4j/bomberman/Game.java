package ru.job4j.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс игры.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Game {

    private final ReentrantLock[][] board;
    private final ExecutorService pool = Executors.newFixedThreadPool(2);
    private final List<Moveble> gamers;

    public Game(int rows, int cols) {
        this.board = new ReentrantLock[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        this.gamers = new ArrayList<>(List.of(new Bomberman(board, new Cell(0, 0))));
    }

    /**
     * Запускает всех игроков в отдельных потоках.
     */
    public void play() {
        for (Moveble gamer : this.gamers) {
            pool.submit(() -> {
                gamer.set();
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        gamer.move();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
    }

    /**
     * Останавливает игру.
     */
    public void shutdown() {
        this.pool.shutdownNow();
    }

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game(5, 10);
        game.play();
        Thread.sleep(5000);
        game.shutdown();
    }
}
