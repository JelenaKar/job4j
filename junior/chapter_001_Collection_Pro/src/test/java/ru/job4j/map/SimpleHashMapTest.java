package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    private SimpleHashMap<Integer, String> map;

    @Before
    public void beforeTest() {
        map = new SimpleHashMap<>();
        map.insert(1, "Первый");
        map.insert(2, "Второй");
        map.insert(3, "Третий");
        map.insert(4, "Четвёртый");
    }

    /**
     * Тестирование автоматического расширения контейнера.
     */
    @Test
    public void whenOverflowLengthThenCapacityGrowsTwice() {
        assertThat(map.size(), is(4));
        map.insert(6, "Шестой");
        assertThat(map.size(), is(8));
    }

    /**
     * Тестирование замены значения при дублирующемся ключе.
     */
    @Test
    public void whenInsertDuplicateKeyThenReplaceValue() {
        map.insert(4, "Пятый");
        assertThat(map.get(4), is("Пятый"));
    }

    /**
     * Тестирование получение элемента по корректному ключу.
     */
    @Test
    public void whenGetElementWithCorrectKeyThenReceiveIt() {
        assertThat(map.get(3), is("Третий"));
        assertThat(map.get(1), is("Первый"));
        assertThat(map.get(4), is("Четвёртый"));
        assertThat(map.get(2), is("Второй"));
    }

    /**
     * Тестирование получение элемента по несущетсвующему ключу.
     */
    @Test
    public void whenTryToGetElementWithNonexistentKeyThenReturnNull() {
        Object expected = null;
        assertThat(map.get(6), is(expected));
    }

    /**
     * Тестирование удаления элемента.
     */
    @Test
    public void whenDeleteElementThenReceiveNullWithTheKey() {
        Object expected = null;
        assertThat(map.get(2), is("Второй"));
        map.delete(2);
        assertThat(map.get(2), is(expected));
    }

    /**
     * Тестирование поведения итератора при конкурентном изменении коллекции.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenWasAddedThenIteratorThrowsConcurrentModificationException() {
        Iterator iterator = map.iterator();
        iterator.next();
        map.insert(17, "Семнадцатый");
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenWasDeletedThenIteratorThrowsConcurrentModificationException() {
        Iterator iterator = map.iterator();
        iterator.next();
        map.delete(1);
        iterator.next();
    }

    /**
     * Тестирование поведения итератора при выходе за пределы контейнера.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenIteratorOnTheLastElementThenNextThrowsNoSuchException() {
        Iterator iterator = map.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    /**
     * Тестирование независимости двух методов итератора (next и hasNext).
     */
    @Test
    public void testsThatNextMethodDoesntDependsOnHasNextMethodInvocation() {

        SimpleHashMap<Integer, String> expected = new SimpleHashMap<>();
        expected.insert(1, "Первый");
        expected.insert(2, "Второй");
        expected.insert(3, "Третий");
        expected.insert(4, "Четвёртый");

        Iterator iterator = map.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(expected.entrySet()[0]));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(expected.entrySet()[1]));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(expected.entrySet()[2]));
        assertThat(iterator.next(), is(expected.entrySet()[3]));
        assertThat(iterator.hasNext(), is(false));
    }

}