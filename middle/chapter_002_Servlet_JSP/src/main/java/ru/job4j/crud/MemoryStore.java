package ru.job4j.crud;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Объект хранилища в текущей памяти.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class MemoryStore implements Store {

    private static final MemoryStore INSTANCE = new MemoryStore();
    private final Map<Long, User> storage = new ConcurrentHashMap<>();
    private AtomicLong increment = new AtomicLong(0);

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    /**
     * Добавляет пользователя в базу данных.
     * @param user - объект пользователя.
     */
    @Override
    public void add(User user) {
        long id = increment.incrementAndGet();
        user.setId(id);
        this.storage.put(id, user);
    }

    /**
     * Обновляет данные пользователя в базе данных.
     * @param user - объект пользователя.
     */
    @Override
    public void update(User user) {
        user.setCreateDate(this.findById(user.getId()).getCreateDate());
        this.storage.replace(user.getId(), user);
    }

    /**
     * Удаляет пользователя из базы данных.
     * @param user - объект пользователя.
     */
    @Override
    public void delete(User user) {
        this.storage.remove(user.getId());
    }

    /**
     * Возвращает список всех пользователей из БД.
     * @return список всех пользователей.
     */
    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.storage.values());
    }

    /**
     * Возвращает пользователя по его id в БД.
     * @param id - id пользователя.
     * @return объект пользователя.
     */
    @Override
    public User findById(long id) {
        return this.storage.get(id);
    }

    @Override
    public User findByLogin(String login) {
        Optional<User> founded = this.storage.values().stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst();
        return founded.get();
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> founded = this.storage.values().stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst();
        return founded.get();
    }
}
