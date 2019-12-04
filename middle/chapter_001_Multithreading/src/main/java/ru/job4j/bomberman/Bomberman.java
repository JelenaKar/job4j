package ru.job4j.bomberman;

/**
 * Класс живого игрока.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class Bomberman extends Gamer {

    public Bomberman(Board board) {
        super(board);
    }

    @Override
    public void run() {
        super.run();
        while (!Thread.currentThread().isInterrupted()) {
            Cell dist = this.getCellFromUser();
            try {
                if (board.move(this.location, dist)) {
                    this.location.row = dist.row;
                    this.location.col = dist.col;
                    System.out.println(Thread.currentThread().getName() + " Bomberman - " + location);
                }
                Thread.sleep(1000);
                if (board.wasCaptured(location)) {
                    System.out.println("Игрока съели");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Метод, запрашивающий ход у пользователя.
     * На данном этапе используется заглушка.
     * @return ячейку для хода.
     */
    public Cell getCellFromUser() {
        return board.randomCell();
    }
}