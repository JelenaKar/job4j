package ru.job4j.xmlconverter;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;

/**
 * Класс, инкапсулирующий конвертацию XML-файла из одного вида в другой.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class ConvertXSQT {

    /**
     * Метод, производящий конвертацию xml-файла из одного формата в другой.
     * @return сумму всех элементов таблицы.
     */
    public static File convert(File source, File dest, File scheme) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(new FileInputStream(scheme)));
        transformer.transform(new StreamSource(new FileInputStream(source)), new StreamResult(dest));
        return dest;
    }
}
