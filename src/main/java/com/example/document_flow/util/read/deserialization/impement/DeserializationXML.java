package com.example.document_flow.util.read.deserialization.impement;

import com.example.document_flow.util.read.deserialization.abstraction.Deserialization;
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
public class DeserializationXML implements Deserialization {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeserializationXML.class.getName());

    private static DeserializationXML deserializationXML;

    private DeserializationXML() {
    }

    /**
     * Десериализует объект(ы) из формата XML
     *
     * @param filePath путь к файлу XML
     * @param type     к какому типу объекта, маппить xml
     * @return десериализуемый объект
     */
    @Override
    public <T> T get(File filePath, Class<T> type) {
        T object = null;
        try {
            JAXBContext contextRead = JAXBContext.newInstance(type);
            Unmarshaller marshaller = contextRead.createUnmarshaller();
            object = (T) marshaller.unmarshal(new File(filePath.getPath()));
        } catch (JAXBException e) {
            LOGGER.warn("Ошибка маппинга обьекта", e);
        }
        return object;
    }

    /**
     * Десериализует из файлов список объектов
     *
     * @param filePaths список расположений/пути к файлу
     * @param type      к какому типу объекта маппить xml
     * @return список сериализованных объектов
     */
    @Override
    public <T> List<T> getList(Set<File> filePaths, Class<T> type) {
        List<T> objectsList = new ArrayList<>();
        filePaths.forEach(pathName -> objectsList.add(get(pathName, type)));
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
