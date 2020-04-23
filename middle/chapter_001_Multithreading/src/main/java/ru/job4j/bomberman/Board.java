package ru.job4j.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Класс игры.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Board {

    private final ReentrantLock[][] board;
    private final ExecutorService pool;

    public Board(int rows, int cols, int occupied, int demons) {
        if (rows * cols <= (occupied + demons + 1)) {
            throw new IllegalStateException("No free cells for game");
        }
        this.board = new ReentrantLock[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        while (occupied > 0) {
            if (this.set(this.randomCell())) {
                occupied--;
            }
        }
        new Thread(new Bomberman(this)).start();
        if ((demons + 1) < Runtime.getRuntime().availableProcessors()) {
            this.pool = Executors.newFixedThreadPool(demons, new Demon.DaemonThreadFactory());
        } else {
            int nThreads = (Runtime.getRuntime().availableProcessors() != 1) ? Runtime.getRuntime().availableProcessors() - 1 : 1;
            this.pool = Executors.newFixedThreadPool(nThreads, new Demon.DaemonThreadFactory());
        }
        IntStream.range(0, demons).forEach(i -> this.pool.submit(new Demon(this)));
    }

    /**
     * Перемещает игрока из одной ячейки в другую.
     * @param src - текущая ячейка.
     * @param dist - новая ячейка.
     * @return true если перемещение удалось, false если ячейка не пуста.
     * @throws InterruptedException в случае прерывания во время попытки заблокировать ячейку.
     */
    public boolean move(Cell src, Cell dist) throws InterruptedException {
        boolean res = false;
        if (board[dist.row][dist.col].tryLock(500, TimeUnit.MILLISECONDS)) {
            board[src.row][src.col].unlock();
            res = true;
        }
        return res;
    }

    /**
     * Установить игрока в заданную ячейку.
     * @param src координата ячейки для установки.
     * @return true в случае успешного размещения игрока, false - если ячейка уже занята.
     */
    public boolean set(Cell src) {
        return board[src.row][src.col].tryLock();
    }

    /**
     * Получение случайной ячейки.
     * @return случайную ячейку из возможного диапазона.
     */
    public Cell randomCell() {
        int row = (int) (Math.random() * board.length), col = (int) (Math.random() * board[0].length);
        return new Cell(row, col);
    }

    /**
     * Проверяет была ли попытка захвата ячейки.
     * @param src адрес проверяемой ячейки.
     * @return true - если попытка захвата предпринималась, false - в противном случае.
     */
    public boolean wasCaptured(Cell src) {
        return board[src.row][src.col].hasQueuedThreads();
    }

    public static void main(String[] args) {
        //new Board(4, 5, 4, 3);
        List<String> test = new ArrayList<>();
        test.add("Hel");
    }
}
