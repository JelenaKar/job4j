package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс абстрактного ирока.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public abstract class Gamer implements Runnable {

    protected final Board board;
    protected final Cell location;

    public Gamer(Board board) {
        this.board = board;
        this.location = new Cell(0, 0);
    }

    /**
     * Устанавливает игрока в свободную ячейку.
     */
    @Override
    public void run() {
        Cell start;
        do {
            start = board.randomCell();
        } while (!board.set(start));
        this.location.row = start.row;
        this.location.col = start.col;
    }
}
