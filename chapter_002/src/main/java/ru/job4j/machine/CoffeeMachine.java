package ru.job4j.machine;

import java.util.Arrays;

/**
 * Класс кофе-машина.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CoffeeMachine {
    private static final int[] coins = {10, 5, 2, 1};

    /**
     * Выдаёт сдачу покупателю.
     * @param value купюра, которую загрузили в кофе-машину.
     * @param price стоимость кофе.
     * @return массив монет для сдачи.
     * @throws NotEnoughMoney - исключение, когда денег недостаточно.
     */
    public int[] changes(int value, int price) {
        int diff = value - price;
        int[] rst = {0};
        int position = 0;
        if (diff >= 0) {
            for (int coin : coins) {
                do {
                    if (diff - coin >= 0) {
                        diff -= coin;
                        rst = Arrays.copyOf(rst, ++position);
                        rst[position-1] = coin;
                    } else {
                        break;
                    }
                } while (diff != 0);
                if (diff == 0) {
                    break;
                }
            }
        } else throw new NotEnoughMoney("You haven't enough money for this coffee!");
        return rst;
    }
}
