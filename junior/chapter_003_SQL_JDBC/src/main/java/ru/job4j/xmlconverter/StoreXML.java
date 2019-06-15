package ru.job4j.xmlconverter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Класс, инкапсулирующий сохранение данных из БД в виде XML-файла.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class StoreXML {

    private File target;

    StoreXML(File target) {
        this.target = target;
    }
    /**
     * Метод, сохраняющий выгрузку из БД в xml-файл.
     */
    public void save() throws Exception {
        try (StoreSQL sql = new StoreSQL(new Config())) {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlUsage.Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(new XmlUsage.Entries(sql.load()), target);
        }
    }
}
