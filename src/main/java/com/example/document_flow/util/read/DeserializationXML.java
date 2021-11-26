package com.example.document_flow.util.read;

import com.example.document_flow.entity.staff.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Этот класс занимается десериализации обьектов из формата XML
 *
 * @author Баратов Руслан
 */
public class DeserializationXML<T extends Staff> {

    private static final Logger log = LoggerFactory.getLogger(DeserializationXML.class.getName());

    private static DeserializationXML deserializationXML;

    private DeserializationXML() {
    }

    /**
     * Десериализует обьект из формата XML
     *
     * @param pathName имя файла XML
     * @param type     к какому типу обьекта, маппить xml
     * @return десериализуемый обьект
     */
    public T fromXMl(String pathName, Class<T> type) {
        T object = null;
        try {
            JAXBContext contextRead = JAXBContext.newInstance(type);
            Unmarshaller marshaller = contextRead.createUnmarshaller();
            object = (T) marshaller.unmarshal(new File(pathName));
        } catch (JAXBException e) {
            log.warn("Ошибка маппинга обьекта", e);
        }
        return object;
    }

    /**
     * Десериализует список обьектов
     *
     * @param pathNames список наименнований обьектов формате XML для сериализации
     * @param type      к какому типу обьекта маппить xml
     * @return список сериализованных обьектов
     */
    public List<T> fromXMl(Set<String> pathNames, Class<T> type) {
        List<T> objectsList = new ArrayList<>();
        pathNames.forEach(pathName -> objectsList.add(fromXMl(pathName, type)));
        return objectsList;
    }

    /**
     * @return синголтон обьект
     */
    public static DeserializationXML getInstance() {
        if (deserializationXML == null) {
            deserializationXML = new DeserializationXML();
        }
        return deserializationXML;
    }
}
