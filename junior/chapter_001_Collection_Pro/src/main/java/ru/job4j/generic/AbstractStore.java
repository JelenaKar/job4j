package ru.job4j.generic;

/**
 * Класс абстрактного хранилища.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> database;

    protected AbstractStore(int size) {
        this.database = new SimpleArray<>(size);
    }

    /**
     * Добавляет новый элемент в хранилище.
     * @param model - новый элемент.
     * @throws ArrayIndexOutOfBoundsException если массив переполнен.
     */
    @Override
    public void add(T model) {
        this.database.add(model);
    }

    /**
     * Заменяет элемент массива по id элемента.
     * @param id - id элемента.
     * @param model - новое значение элемента.
     * @return true - если id найден и замена прошла успешно, false - если id не был найден.
     */
    @Override
    public boolean replace(String id, T model) {
        boolean res = false;
        int index = this.getIndexByStringId(id);
        if (index != -1) {
            res = true;
            this.database.set(index, model);
        }
        return res;
    }

    /**
     * Удаляет элемент массива по id элемента.
     * @param id - id элемента.
     * @return true - если id найден и удаление прошло успешно, false - если id не был найден.
     */
    @Override
    public boolean delete(String id) {
        boolean res = false;
        int index = this.getIndexByStringId(id);
        if (index != -1) {
            res = true;
            this.database.remove(index);
        }
        return res;
    }

    /**
     * Возвращает элемент хранилища по id.
     * @param id - id элемента.
     * @return элемент массива если id найден, null - если id не был найден.
     */
    @Override
    public T findById(String id) {
        T res = null;
        int index = this.getIndexByStringId(id);
        if (index != -1) {
            res = this.database.get(index);
        }
        return res;
    }

    /**
     * Получает число элементов в хранилище.
     * @return текущее количество элементов.
     */
    public int size() {
        return this.database.size();
    }

    private int getIndexByStringId(String id) {
        int index = -1;
        for (int i = 0; i < this.database.size(); i++) {
            if (this.database.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
