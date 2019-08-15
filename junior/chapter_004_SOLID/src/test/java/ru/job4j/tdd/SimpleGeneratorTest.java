package ru.job4j.tdd;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class SimpleGeneratorTest {

    /**
     * Тест замены ключей в шаблоне на значения.
     */
    @Test
    public void whenUseKeysThenShouldBeSubstituted() throws KeyNotFoundException, RedundantKeyException {
        String text = "Привет, меня зовут ${name}. Мне ${years} лет.";
        SimpleGenerator sg = new SimpleGenerator(new HashSet<>(Set.of("name", "years")));
        String expected = "Привет, меня зовут Вася. Мне 20 лет.";
        assertThat(sg.generate(text, new HashMap<>(Map.of("name", "Вася", "years", "20"))), is(expected));
    }

    /**
     * Тест случая, когда в шаблоне присутствуют неизвестные ключи.
     */
    @Test (expected = RedundantKeyException.class)
    public void whenUseUnknownKeysInTemplateThenThrowException() throws KeyNotFoundException, RedundantKeyException {
        String text = "Привет, меня зовут ${name}. Мне ${years} лет. Я ${sex} пола.";
        SimpleGenerator sg = new SimpleGenerator(new HashSet<>(Set.of("name", "years")));
        System.out.println(sg.generate(text, new HashMap<>(Map.of("name", "Вася", "years", "20"))));
    }

    /**
     * Тест случая, когда в карте присутствуют неизвестные ключи.
     */
    @Test (expected = RedundantKeyException.class)
    public void whenUseUnknownKeysInMapThenThrowException() throws KeyNotFoundException, RedundantKeyException {
        String text = "Привет, меня зовут ${name}. Мне ${years} лет.";
        SimpleGenerator sg = new SimpleGenerator(new HashSet<>(Set.of("name", "years")));
        System.out.println(sg.generate(text, new HashMap<>(Map.of("name", "Вася", "years", "20", "sex", "мужского"))));
    }

    /**
     * Тест случая, когда в шаблоне отсутствуют обязательные ключи.
     */
    @Test (expected = KeyNotFoundException.class)
    public void whenMissingRequiredKeysInTemplateThenThrowException() throws KeyNotFoundException, RedundantKeyException {
        String text = "Привет, меня зовут ${name}. Мне лет.";
        SimpleGenerator sg = new SimpleGenerator(new HashSet<>(Set.of("name", "years")));
        System.out.println(sg.generate(text, new HashMap<>(Map.of("name", "Вася", "years", "20"))));
    }

}