package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PointTest {
    /**
     * Test distanceTo method.
     */
    @Test
    public void whenX00AndY34ThenFive() {
        Point a = new Point(0, 0);
        Point b = new Point(3, 4);
        double result = a.distanceTo(b);
        assertThat(result, is(5D));
    }
}