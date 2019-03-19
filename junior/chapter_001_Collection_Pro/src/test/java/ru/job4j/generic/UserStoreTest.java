package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    UserStore<User> store = new UserStore<>(7);

    @Before
    public void setUp() {
        store.add(new User("user1", "Вася"));
        store.add(new User("user2", "Петя"));
        store.add(new User("user3", "Марина"));
        store.add(new User("user4", "Наталья"));
    }

    /**
     * Тестирование добавления элемента.
     */
    @Test
    public void whenAddNewElementThenLengthGrows() {
        store.add(new User("user5", "Федя"));
        assertThat(store.size(), is(5));
    }

    /**
     * Тестирование получение элемента по корректному индексу.
     */
    @Test
    public void whenGetElementWithCorrectIndexThenFindIt() {
        assertThat(store.findById("user3").getName(), is("Марина"));
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
    public void whenReplaceElementWithCorrectSameIdThenReturnTrueAndReceiveNewUserName() {
        assertThat(store.findById("user3").getName(), is("Марина"));
        assertThat(store.replace("user3", new User("user3", "Анатолий")), is(true));
        assertThat(store.findById("user3").getName(), is("Анатолий"));
    }

    /**
     * Тестирование замены элемента по некорректному индексу.
     */
    @Test
    public void whenTryToReplaceElementWithIncorrectIdThenReturnFalse() {
        assertThat(store.replace("user100", new User("user3", "Анатолий")), is(false));
    }

    /**
     * Тестирование удаления элемента по корректному индексу.
     */
    @Test
    public void whenRemoveElementWithCorrectIdThenAfterCanNotFindUserAndReturnTrue() {
        assertThat(store.findById("user3").getName(), is("Марина"));
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