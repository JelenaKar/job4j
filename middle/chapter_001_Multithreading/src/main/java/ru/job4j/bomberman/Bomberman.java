package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс живого игрока.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class Bomberman extends Gamer {

    public Bomberman(ReentrantLock[][] board, Cell location) {
        super(board, location);
    }

    /**
     * Реализует передвижение главного героя.
     * @return true - если ход удалось произвести, false - если выбранная клетка заблокирована другими.
     * @throws InterruptedException - если приложение остановлено во время хода игрока.
     */
    @Override
    public boolean move() throws InterruptedException {
        int row = (int) (Math.random() * board.length), col = (int) (Math.random() * board[0].length);
        boolean result = board[row][col].tryLock(500, TimeUnit.MILLISECONDS);
        if (result) {
            board[this.location.row][this.location.col].unlock();
            this.location.row = row;
            this.location.col = col;
        }
        //Для отладки
        System.out.println(Thread.currentThread().getName() + " - " + location);
        return result;
    }
}