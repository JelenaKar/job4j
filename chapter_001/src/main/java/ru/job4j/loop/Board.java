package ru.job4j.loop;

/**
 * Шахматная доска псевдографикой.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Board {

    /**
     * Рисует шахматную доску псевдографикой заданной размерности.
     * @param width ширина доски.
     * @param height высота доски.
     * @return изображение шахматной доски.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}
