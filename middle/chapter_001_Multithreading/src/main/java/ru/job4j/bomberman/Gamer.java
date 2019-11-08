package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс абстрактного ирока.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public abstract class Gamer implements Moveble {

    protected final ReentrantLock[][] board;
    protected final Cell location;

    public Gamer(ReentrantLock[][] board, Cell location) {
        this.board = board;
        this.location = location;
    }

    /**
     * Осуществляет установку героя на игровую доску.
     */
    @Override
    public void set() {
        while (!board[location.row][location.col].tryLock()) {
            location.row = (int) (Math.random() * board.length);
            location.col = (int) (Math.random() * board[0].length);
        }
    }
}
