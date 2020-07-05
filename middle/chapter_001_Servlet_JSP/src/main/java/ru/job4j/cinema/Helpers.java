package ru.job4j.cinema;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс вспомогательных функций.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Helpers {

    /**
     * Конвертирует список в массив javascript.
     * @param list - список Java.
     * @return массив javascript в виде строки.
     */
    public static String listToJavascript(List<String> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append("\"").append(list.get(i)).append("\"");
            if (i + 1 < list.size()) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Конвертирует список мест в матрицу.
     * @param places - список всех мест.
     * @return все места в виде матрицы ряд/место.
     */
    public static List<List<Place>> listToMatrix(List<Place> places) {
        List<List<Place>> res = new ArrayList<>();
        int idrow = places.get(0).getRow();
        List<Place> row = new ArrayList<>();
        for (Place place : places) {
            if (place.getRow() != idrow) {
                res.add(row);
                idrow = place.getRow();
                row = new ArrayList<>();
            }
            row.add(place);
        }
        res.add(row);
        return res;
    }

    /**
     * Возвращает максимальную ширину зала.
     * @param matrix - зал в виде матрицы.
     * @return максимальную ширину зала.
     */
    public static int getMatrixWidth(List<List<Place>> matrix) {
        int max = matrix.get(0).size();
        for (List<Place> row : matrix) {
            if (row.size() > max) {
                max = row.size();
            }
        }
        return max;
    }
}
