package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс телефонный справочник.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PhoneDictionary {
    private List<Person> persons = new ArrayList<>();

    /**
     * Добавить нового пользователя в справочник.
     * @param person Объект пользователя.
     */
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person person : this.persons) {
            if (person.getName().contains(key) || person.getSurname().contains(key) || person.getAddress().contains(key)
                    || person.getPhone().contains(key)) {
                result.add(person);
            }
        }
        if (result.size() == 0) {
            result = null;
        }
        return result;
    }
}
