package ru.job4j.tictactoe;

/**
 * Класс абстрактного пользователя.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public abstract class User implements UserInterface {

    protected int sign;
    protected int size;

    public User(int sign, int size) {
        this.sign = sign;
        this.size = size;
    }

    public int getSign() {
        return sign;
    }

}
