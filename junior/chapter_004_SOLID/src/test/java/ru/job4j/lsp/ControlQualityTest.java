package ru.job4j.lsp;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class ControlQualityTest {

    /**
     * Тест пересортировки продуктов по хранилищам.
     */
    @Test
    public void whenSortAccordingToExpiredDate() {
        Warehouse wh = new Warehouse(new LinkedList<>(
                List.of(new Cheese("Happy Cow", System.currentTimeMillis() - 1036800000L, 230),
                        new Eggs("Курочка Ряба", System.currentTimeMillis() - 1123200000L, 100))
        ));

        Shop shop = new Shop(new LinkedList<>(
                List.of(new Milk("Домашнее", System.currentTimeMillis() - 518400000L, 150),
                        new Eggs("Моя ферма", System.currentTimeMillis() - 1728000000L, 120))
        ));
        Trash trash = new Trash(new LinkedList<>());
        ControlQuality cc = new ControlQuality(List.of(trash, shop, wh));
        cc.sortProducts();

        Queue<Food> whProducts = wh.getStorage();
        assertThat(whProducts.size(), is(1));
        assertThat(whProducts.peek().getName(), is("Happy Cow"));
        assertThat(whProducts.peek().getPrice(), is(230.0));

        Queue<Food> shopProducts = shop.getStorage();
        assertThat(shopProducts.size(), is(2));

        Food food = shopProducts.poll();
        assertThat(food.getName(), is("Моя ферма"));
        assertThat(food.getPrice(), is(72.0));
        food = shopProducts.poll();
        assertThat(food.getName(), is("Курочка Ряба"));
        assertThat(food.getPrice(), is(100.0));

        Queue<Food> trashProducts = trash.getStorage();
        assertThat(trashProducts.size(), is(1));
        assertThat(trashProducts.peek().getName(), is("Домашнее"));
        assertThat(trashProducts.peek().getPrice(), is(0.0));
    }

    /**
     * Тест случая, когда срок годности израсходован менее, чем на 25%.
     */
    @Test
    public void whenExpirePeriodLessThan25PercentThenAddToWharehouse() {
        Warehouse wh = new Warehouse(new LinkedList<>(
                List.of(new Cheese("Happy Cow", System.currentTimeMillis() - 1036800000L, 230))
        ));
        Shop shop = new Shop(new LinkedList<>());
        Trash trash = new Trash(new LinkedList<>());
        ControlQuality cc = new ControlQuality(List.of(wh, shop, trash));
        cc.sortProducts();

        Queue<Food> whProducts = wh.getStorage();
        assertThat(whProducts.size(), is(1));
        assertThat(whProducts.peek().getName(), is("Happy Cow"));
        assertThat(whProducts.peek().getPrice(), is(230.0));

        assertThat(shop.getStorage().isEmpty(), is(true));

        assertThat(trash.getStorage().isEmpty(), is(true));
    }

    /**
     * Тест случая, когда срок годности израсходован больше, чем на 25% включительно, но меньше 75%.
     */
    @Test
    public void whenExpirePeriodMoreThan25PercentsAndLessThan75PercentsThenAddToShop() {

        Warehouse wh = new Warehouse(new LinkedList<>(
                List.of(new Eggs("Курочка Ряба", System.currentTimeMillis() - 1123200000L, 100))
        ));
        Shop shop = new Shop(new LinkedList<>());
        Trash trash = new Trash(new LinkedList<>());
        ControlQuality cc = new ControlQuality(List.of(wh, shop, trash));
        cc.sortProducts();

        assertThat(wh.getStorage().isEmpty(), is(true));

        Queue<Food> shopProducts = shop.getStorage();
        assertThat(shopProducts.size(), is(1));
        assertThat(shopProducts.peek().getName(), is("Курочка Ряба"));
        assertThat(shopProducts.peek().getPrice(), is(100.0));

        assertThat(trash.getStorage().isEmpty(), is(true));
    }

    /**
     * Тест случая, когда срок годности израсходован больше, чем на 75% включительно, но меньше 100%.
     */
    @Test
    public void whenExpirePeriodMoreThan75PercentsAndLessThan100PercentsThenAddToShopAndDiscountPrice() {
        Warehouse wh = new Warehouse(new LinkedList<>(
                List.of(new Eggs("Моя ферма", System.currentTimeMillis() - 1728000000L, 120))
        ));
        Shop shop = new Shop(new LinkedList<>());
        Trash trash = new Trash(new LinkedList<>());
        ControlQuality cc = new ControlQuality(List.of(wh, shop, trash));
        cc.sortProducts();

        assertThat(wh.getStorage().isEmpty(), is(true));

        Queue<Food> shopProducts = shop.getStorage();
        assertThat(shopProducts.size(), is(1));
        assertThat(shopProducts.peek().getName(), is("Моя ферма"));
        assertThat(shopProducts.peek().getPrice(), is(72.0));

        assertThat(trash.getStorage().isEmpty(), is(true));
    }

    /**
     * Тест случая, когда срок годности израсходован больше, чем на 75% включительно, но меньше 100%.
     */
    @Test
    public void whenExpirePeriodMoreThan100PercentsThenAddToTrash() {
        Warehouse wh = new Warehouse(new LinkedList<>(
                List.of(new Milk("Домашнее", System.currentTimeMillis() - 518400000L, 150))
        ));
        Shop shop = new Shop(new LinkedList<>());
        Trash trash = new Trash(new LinkedList<>());
        ControlQuality cc = new ControlQuality(List.of(wh, shop, trash));
        cc.sortProducts();

        assertThat(wh.getStorage().isEmpty(), is(true));
        assertThat(shop.getStorage().isEmpty(), is(true));

        Queue<Food> trashProducts = trash.getStorage();
        assertThat(trashProducts.size(), is(1));
        assertThat(trashProducts.peek().getName(), is("Домашнее"));
        assertThat(trashProducts.peek().getPrice(), is(0.0));
    }

}