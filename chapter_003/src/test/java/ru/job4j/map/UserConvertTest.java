package ru.job4j.map;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserConvertTest {

    /**
     * Тестирование преобразования списка пользователей в карту.
     */
    @Test
    public void whenListToHashMap() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "Ivan Ivanov", "Krasnoyarsk"));
        list.add(new User(2, "Petr Petrov", "Belgorod"));

        UserConvert converter = new UserConvert();

        HashMap<Integer, User> expect = new HashMap<>(2);
        expect.put(1, new User(1, "Ivan Ivanov", "Krasnoyarsk"));
        expect.put(2, new User(2, "Petr Petrov", "Belgorod"));

        assertThat(converter.process(list), is(expect));
    }
}
