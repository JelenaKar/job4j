package ru.job4j.xmlconverter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Класс, инкапсулирующий парсинг XML-файла.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class ParseXML {

    private int sum;
    private File sourse;

    public ParseXML(File source) {
        this.sourse = source;
    }

    /**
     * Метод, производящий парсинг преобразованного xml-файла..
     * @return сумму всех элементов таблицы.
     * @throws ParserConfigurationException, SAXException, IOException
     */
    public int sumOfAllElements() throws ParserConfigurationException, SAXException, IOException {
       SAXParserFactory factory = SAXParserFactory.newInstance();
       SAXParser parser = factory.newSAXParser();

       XMLHandler handler = new XMLHandler();
       parser.parse(this.sourse, handler);
       return this.sum;
   }

    private class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("entry")) {
                sum += Integer.valueOf(attributes.getValue("field"));
            }
        }
    }

}
