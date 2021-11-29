package com.example.document_flow.util.write;

import com.example.document_flow.entity.staff.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Это класс занимается сериализацией обьектов в формат xml
 *
 * @author Баратов Руслан
 */
public class SerializableXML<T extends Staff> {

    private final Logger LOGGER = LoggerFactory.getLogger(SerializableXML.class.getName());

    private static SerializableXML serializableXML;

    private SerializableXML() {
    }

    /**
     * Сериализует лист обьектов в формат xml
     *
     * @return Set путь к файлам
     */
    public Set<String> toXML(List<T> objects) {
        Set<String> filesPath = new HashSet<>();
        objects.stream().forEach(object -> filesPath.add(toXML(object)));
        return filesPath;
    }

    /**
     * Сериализует объект в формат xml
     *
     * @param object обьект для сериализации
     * @return путь к файлу
     */
    public String toXML(T object) {
        String filePath = object.getClass().getSimpleName() + object.getId() + ".xml";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, new File(filePath));
        } catch (JAXBException e) {
            LOGGER.warn("Exception ", e);
        }
        return filePath;
    }

    /**
     * @return синголтон обьект
     */
    public static SerializableXML getInstance() {
        if (serializableXML == null) {
            serializableXML = new SerializableXML();
        }
        return serializableXML;
    }
}