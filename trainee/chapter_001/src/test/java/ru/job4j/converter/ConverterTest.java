package ru.job4j.converter;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Converter Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConverterTest {

    /**
     * Test rubleToDollar method.
     */
    @Test
    public void when60RubleToDollarThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToDollar(60);
        assertThat(result, is(1));
    }

    /**
     * Test rubleToEuro method.
     */
    @Test
    public void when70RubleToEuroThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToEuro(70);
        assertThat(result, is(1));
    }

    /**
     * Test dollarToRuble method.
     */
    @Test
    public void when2DollarToRubleThen120() {
        Converter converter = new Converter();
        int result = converter.dollarToRuble(2);
        assertThat(result, is(120));
    }

    /**
     * Test euroToRuble method.
     */
    @Test
    public void when2EuroToRubleThen140() {
        Converter converter = new Converter();
        int result = converter.euroToRuble(2);
        assertThat(result, is(140));
    }
}
