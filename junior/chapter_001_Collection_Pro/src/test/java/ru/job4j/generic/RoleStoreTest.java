package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RoleStoreTest {

    RoleStore<Role> store = new RoleStore<>(7);

    @Before
    public void setUp() {
        store.add(new Role("user1", "Системный администратор"));
        store.add(new Role("user2", "Backend разработчик"));
        store.add(new Role("user3", "Frontend разработчик"));
        store.add(new Role("user4", "Бухгалтер"));
    }

    /**
     * Тестирование добавления элемента.
     */
    @Test
    public void whenAddNewElementThenLengthGrows() {
        store.add(new Role("user5", "Дизайнер"));
        assertThat(store.size(), is(5));
    }

    /**
     * Тестирование получение элемента по корректному индексу.
     */
    @Test
    public void whenGetElementWithCorrectIndexThenFindIt() {
        assertThat(store.findById("user3").getAction(), is("Frontend разработчик"));
    }

    /**
     * Тестирование получение элемента по некорректному индексу.
     */
    @Test
    public void whenTryToGetElementWithIncorrectIndexThenReturnNull() {
        User expected = null;
        assertThat(store.findById("user180"), is(expected));
    }

    /**
     * Тестирование замены элемента по корректному индексу.
     */
    @Test
    public void whenReplaceElementWithCorrectSameIdThenReturnTrueAndReceiveNewAction() {
        assertThat(store.findById("user3").getAction(), is("Frontend разработчик"));
        assertThat(store.replace("user3", new Role("user3", "Верстальщик")), is(true));
        assertThat(store.findById("user3").getAction(), is("Верстальщик"));
    }

    /**
     * Тестирование замены элемента по некорректному индексу.
     */
    @Test
    public void whenTryToReplaceElementWithIncorrectIdThenReturnFalse() {
        assertThat(store.replace("user100", new Role("user3", "Верстальщик")), is(false));
    }

    /**
     * Тестирование удаления элемента по корректному индексу.
     */
    @Test
    public void whenRemoveElementWithCorrectIdThenAfterCanNotFindActionAndReturnTrue() {
        assertThat(store.findById("user3").getAction(), is("Frontend разработчик"));
        assertThat(store.delete("user3"), is(true));
        User expected = null;
        assertThat(store.findById("user3"), is(expected));
    }

    /**
     * Тестирование удаления элемента по некорректному индексу.
     */
    @Test
    public void whenTryToDeleteElementWithIncorrectIndexThenReturnFalse() {
        assertThat(store.delete("user100"), is(false));
    }

}