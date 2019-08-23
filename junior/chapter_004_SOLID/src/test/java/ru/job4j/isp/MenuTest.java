package ru.job4j.isp;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class MenuTest {

    /**
     * Тест вывода пунктов меню по умолчанию.
     */
    @Test
    public void whenUseDefaultTab() throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            MenuItemBase.out = new PrintStream(outputStream);
            MenuItemActive item1 = new MenuItemActive("List1", new LinkedList<>(
                    List.of(
                            new MenuItemNonActive("List1.1", new LinkedList<>(
                                    List.of(new MenuItemActive("List1.1.1", MenuItemBase.out::println),
                                            new MenuItemNonActive("List1.1.2"))
                            )),
                            new MenuItemNonActive("List1.2")
                    )
            ), MenuItemBase.out::println);

            MenuItemNonActive item2 = new MenuItemNonActive("List2", new LinkedList<>(
                    List.of(new MenuItemNonActive("List2.1"))
            ));

            MenuItemActive item3 = new MenuItemActive("List3", MenuItemBase.out::println);

            Menu menu = new Menu(new LinkedList<>(List.of(item1, item2, item3)));
            menu.print();

            StringBuilder expected = new StringBuilder();
            expected.append("List1 ✔").append(System.lineSeparator())
                    .append("--List1.1 ❌").append(System.lineSeparator())
                    .append("----List1.1.1 ✔").append(System.lineSeparator())
                    .append("----List1.1.2 ❌").append(System.lineSeparator())
                    .append("--List1.2 ❌").append(System.lineSeparator())
                    .append("List2 ❌").append(System.lineSeparator())
                    .append("--List2.1 ❌").append(System.lineSeparator())
                    .append("List3 ✔").append(System.lineSeparator());

            assertThat(outputStream.toString(), is(expected.toString()));
        }
    }

    /**
     * Тест вывода пунктов меню с табуляцией в виде пробелов.
     */
    @Test
    public void whenUseWhitespaceAsTab() throws IOException {
        MenuItemBase.tabSymbol = "  ";
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            MenuItemBase.out = new PrintStream(outputStream);
            MenuItemActive item1 = new MenuItemActive("List1", new LinkedList<>(
                    List.of(
                            new MenuItemNonActive("List1.1", new LinkedList<>(
                                    List.of(new MenuItemActive("List1.1.1", MenuItemBase.out::println),
                                            new MenuItemNonActive("List1.1.2"))
                            )),
                            new MenuItemNonActive("List1.2")
                    )
            ), MenuItemBase.out::println);

            MenuItemNonActive item2 = new MenuItemNonActive("List2", new LinkedList<>(
                    List.of(new MenuItemNonActive("List2.1"))
            ));

            MenuItemActive item3 = new MenuItemActive("List3", MenuItemBase.out::println);

            Menu menu = new Menu(new LinkedList<>(List.of(item1, item2, item3)));
            menu.print();

            StringBuilder expected = new StringBuilder();
            expected.append("List1 ✔").append(System.lineSeparator())
                    .append("  List1.1 ❌").append(System.lineSeparator())
                    .append("    List1.1.1 ✔").append(System.lineSeparator())
                    .append("    List1.1.2 ❌").append(System.lineSeparator())
                    .append("  List1.2 ❌").append(System.lineSeparator())
                    .append("List2 ❌").append(System.lineSeparator())
                    .append("  List2.1 ❌").append(System.lineSeparator())
                    .append("List3 ✔").append(System.lineSeparator());

            assertThat(outputStream.toString(), is(expected.toString()));
        }
    }

    /**
     * Тест вызова активного и неактивного пунктов меню.
     */
    @Test
    public void whenExecuteActiveAndNonActiveItems() throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            MenuItemBase.out = new PrintStream(outputStream);
            MenuItemActive item1 = new MenuItemActive("List1", MenuItemBase.out::println);

            MenuItemNonActive item2 = new MenuItemNonActive("List2");

            Menu menu = new Menu(new LinkedList<>(List.of(item1, item2)));
            menu.print();
            item1.execute();
            item2.execute();

            StringBuilder expected = new StringBuilder();
            expected.append("List1 ✔").append(System.lineSeparator())
                    .append("List2 ❌").append(System.lineSeparator())
                    .append("List1").append(System.lineSeparator())
                    .append("Раздел в разработке").append(System.lineSeparator());

            assertThat(outputStream.toString(), is(expected.toString()));
        }
    }

}