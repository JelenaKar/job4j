package ru.job4j.bomberman;

import java.util.concurrent.ThreadFactory;

/**
 * Класс чудовища.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Demon extends Gamer {

    public Demon(Board board) {
        super(board);
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            Cell dist = this.board.randomCell();
            try {
                if (board.move(this.location, dist)) {
                    this.location.row = dist.row;
                    this.location.col = dist.col;
                    System.out.println(Thread.currentThread().getName() + " Demon - " + location);
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Фабрика для создания чудовищ в виде "демонических" потоков.
     */
    public static class DaemonThreadFactory implements ThreadFactory {
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        }
    }
}
