package com.example.document_flow.util.read;

import com.example.document_flow.entity.staff.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Этот класс занимается десериализации обьектов из формата XML
 *
 * @author Баратов Руслан
 */
public class DeserializationXML<T extends Staff> {

    private HashMap<String, T> objectMap = new HashMap<>();

    private static final Logger log = LoggerFactory.getLogger(DeserializationXML.class.getName());

    /**
     * Десериализует обьект.
     * Если требуемый обьект уже был сериализован, возращаем его из маппы.
     * Если нет, маппип из файла XML
     * @param pathName наименования файла с форматом xml для десериализации обьекта
     * @param type     к какому типу обьекта маппить xml
     * @return Десериализованный обьект
     */
    public T deserialization(String pathName, Class<T> type) {
        if ((objectMap.containsKey(pathName)) && (objectMap.get(pathName).getClass() == type)) {
            return getFromMap(pathName);
        } else {
            return getFromXML(pathName, type);
        }
    }

    /**
     * Десериализует список обьектов
     *
     * @param pathNames список наименнований обьектов формате XML для сериализации
     * @param type      к какому типу обьекта маппить xml
     * @return список сериализованных обьектов
     */
    public List<T> deserialization(Set<String> pathNames, Class<T> type) {
        List<T> objectsList = new ArrayList<>();
        for (String pathName : pathNames) {
            objectsList.add(deserialization(pathName, type));
        }
        return objectsList;
    }

    /**
     * Получаем обьект из маппы
     * @param pathName имя файла XML
     * @return десериализуемый обьект
     */
    private T getFromMap(String pathName) {
        return objectMap.get(pathName);
    }

    /**
     * Десериализует обьект из формата XML
     *
     * @param pathName имя файла XML
     * @param type     к какому типу обьекта, маппить xml
     * @return десериализуемый обьект
     */
    private T getFromXML(String pathName, Class<T> type) {
        T object = null;
        try {
            JAXBContext contextRead = JAXBContext.newInstance(type);
            Unmarshaller marshaller = contextRead.createUnmarshaller();
            object = (T) marshaller.unmarshal(new File(pathName));
            objectMap.put(pathName, object);
        } catch (JAXBException e) {
            log.warn("Ошибка маппинга обьекта", e);
        }
        return object;
    }
}
