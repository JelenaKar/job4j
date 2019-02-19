package ru.job4j.machine;

import java.util.Arrays;

/**
 * Класс кофе-машина.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CoffeeMachine {
    private static final int[] COINS = {10, 5, 2, 1};

    /**
     * Выдаёт сдачу покупателю.
     * @param value купюра, которую загрузили в кофе-машину.
     * @param price стоимость кофе.
     * @return массив монет для сдачи.
     * @throws NotEnoughMoney - исключение, когда денег недостаточно.
     */
    public int[] changes(int value, int price) {
        int diff = value - price;
        if (diff < 0) {
            throw new NotEnoughMoney("You haven't enough money for this coffee!");
        } else {
            int[] rst = new int[diff];
            int len = 0;
            for (int coin : COINS) {
                while (diff != 0) {
                    if (diff - coin >= 0) {
                        diff -= coin;
                        rst[len++] = coin;
                    } else {
                        break;
                    }
                }
                if (diff == 0) {
                    len = (len == 0) ? ++len : len;
                    break;
                }
            }
            rst = Arrays.copyOf(rst, len);
            return rst;
        }
    }
}
