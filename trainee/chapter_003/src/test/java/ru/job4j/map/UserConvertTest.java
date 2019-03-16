package ru.job4j.map;

import org.junit.Test;
import java.util.List;
import java.util.Map;

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
        List<User> list = List.of(
            new User(1, "Ivan Ivanov", "Krasnoyarsk"),
            new User(2, "Petr Petrov", "Belgorod")
        );

        UserConvert converter = new UserConvert();

        Map<Integer, User> expect = Map.of(
                1, new User(1, "Ivan Ivanov", "Krasnoyarsk"),
                2, new User(2, "Petr Petrov", "Belgorod")
        );

        assertThat(converter.process(list), is(expect));
    }
}
