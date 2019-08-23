package ru.job4j.tictactoe;

import java.io.IOException;
import java.util.Map;

/**
 * Класс логики игры.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Logic {

    private Map<Integer, User> players;
    private static final Map<Integer, String> NAMES = Map.of(Square.CROSS, "крестики", Square.ZERO, "нолики");
    private int size;
    private int combination;
    private Square square;

    public Logic(Map<Integer, User> players, int size, int combination) {
        this.players = players;
        this.size = size;
        this.combination = combination;
        this.square = new Square(size);
    }

    private int winInCombinationSquare(int offsetX, int offsetY) {
        int win = 0;
        int mdSum = 0;
        int sdSum = 0;
        for (int i = offsetX; i < this.combination + offsetX; i++) {
            int xSum = 0;
            int ySum = 0;
            for (int j = offsetY; j < this.combination + offsetY; j++) {
                xSum += this.square.getMatrix()[i][j];
                ySum += this.square.getMatrix()[j][i];
                if ((i - offsetX) + (j - offsetY) == this.combination - 1) {
                    sdSum += this.square.getMatrix()[i][j];
                }
                if ((i - offsetX) == (j - offsetY)) {
                    int sum = this.square.getMatrix()[i][j];
                    mdSum += sum;
                }
            }
            if (Math.abs(xSum) == this.combination || Math.abs(ySum) == this.combination) {
                win = (Math.abs(xSum) == this.combination) ? xSum / this.combination : ySum / this.combination;
                break;
            }
        }
        if (Math.abs(mdSum) == this.combination || Math.abs(sdSum) == this.combination) {
            win = (Math.abs(mdSum) == this.combination) ? mdSum / this.combination : sdSum / this.combination;
        }
        return win;
    }

    /**
     * Возвращает победителя игры, если он имеется.
     * @return объект типа User если победитель есть, null при отсутствии победителя.
     */
    public User winner() {
        int win = 0;
        for (int i = 0; i < this.size + 1 - this.combination; i++) {
            for (int j = 0; j < this.size + 1 - this.combination; j++) {
                win = this.winInCombinationSquare(i, j);
                if (win != 0) {
                    break;
                }
            }
            if (win != 0) {
                break;
            }
        }
        return this.players.get(win);
    }

    /**
     * Воспроизводит игровой процесс.
     * @throws IOException в случае ошибки ввода/вывода.
     */
    public void play() throws IOException {
        do {
            if (this.isWinningMove(Square.CROSS)) {
                break;
            }

            if (this.isWinningMove(Square.ZERO)) {
                break;
            }

        } while (this.square.hasFree() && this.winner() == null);
        this.square.show();
        if (this.winner() == null) {
            System.out.println("Ничья!");
        } else {
            System.out.println("Победили " + NAMES.get(this.winner().getSign()) + "!");
        }
    }

    private boolean isWinningMove(int sign) throws IOException {
        boolean res = false;
        this.square.show();
        System.out.println("Ходят " + NAMES.get(sign));
        int[] move;
        do {
            move = players.get(sign).move();
        } while (!this.square.insert(move) && this.square.hasFree() && this.winner() == null);
        if (!this.square.hasFree() || this.winner() != null) {
            res = true;
        }
        return res;
    }
}