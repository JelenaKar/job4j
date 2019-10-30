package ru.job4j.concurrent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс неблокирующего кэша.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class NonBlockingCash {

    private ConcurrentHashMap<Integer, Base> store = new ConcurrentHashMap<>();

    /**
     * Получает значение из кэша.
     * @param key - id модели, выступающий ключом.
     * @return строку из кэша в виде объекта класса Base.
     */
    public Base get(int key) {
        return store.get(key);
    }

    /**
     * Добавляет новое значение в кэш.
     * @param model - новый объект базового класса Base.
     * @throws OptimisticException если объект с таким ключом уже существует.
     */
    public void add(Base model) {
        if (store.putIfAbsent(model.getId(), model) != null) {
            throw new OptimisticException("Model with this id is already exists.");
        }
    }

    /**
     * Модифицирует значение модели в кэше.
     * @param model - новое значение модели.
     * @return true - если замена прошла успешно, false - если значение по указанному ключу отсутствовало.
     * @throws OptimisticException при конкурентном изменении несколькими потоками текущего значения кэша.
     */
    public boolean update(Base model) {
        return store.computeIfPresent(model.getId(), (k, v) -> {
            if (model.getVersion() != this.store.get(k).getVersion()) {
                throw new OptimisticException("Concurrent modification of model meaning.");
            }
            model.setVersion(model.getVersion() + 1);
            return model;
        }) != null;
    }

    /**
     * Удаляет значение из кэша.
     * @param model - удаляемое значение.
     * @return true - если удаление прошло успешно, false - если значение по указанному ключу отсутствовало.
     * @throws OptimisticException если текущее значение было конкурентно изменено другим потоком.
     */
    public boolean delete(Base model) {
        if (model.getVersion() != this.store.get(model.getId()).getVersion()) {
            throw new OptimisticException("Concurrent modification of model meaning.");
        }
        return store.remove(model.getId()) != null;
    }
}
