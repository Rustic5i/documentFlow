package com.example.document_flow.util.write;

import com.example.document_flow.entity.staff.Person;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Этот класс занимается десериализации обьектов из формата XML
 */
public class DeserializationXML {

    /**
     *
     * @param pathName наименования файла с форматом xml для десериализации обьекта
     * @return Десериализованный обьект
     * @throws JAXBException – если при создании JAXBContext произошла ошибка
     * Unmarshalexception – Если обработчик ValidationEventHandler возвращает false из своего метода handleEvent
     * или обработчик не может выполнить привязку XML к Java.
     * IllegalArgumentException – Если параметр файла равен нулю
     */
    public Object deserializationFromXml(String pathName) throws JAXBException {
        JAXBContext contextRead = JAXBContext.newInstance(Person.class);

        Unmarshaller marshaller = contextRead.createUnmarshaller();

        return marshaller.unmarshal(new File(pathName));
    }

    /**
     * Десериализует список обьектов
     * @param pathNames список наименнований обьектов формате XML для сериализации
     * @return список сериализованных обьектов
     * @throws JAXBException – если при создании JAXBContext произошла ошибка
     * Unmarshalexception – Если обработчик ValidationEventHandler возвращает false из своего метода handleEvent
     * или обработчик не может выполнить привязку XML к Java.
     * IllegalArgumentException – Если параметр файла равен нулю
     */
    public List<Object> deserializationFromXml(Set<String> pathNames) throws JAXBException {
        List<Object> personList = new ArrayList<>();
        for (String pathName : pathNames) {
            JAXBContext contextRead =JAXBContext.newInstance(Person.class);
            Unmarshaller marshaller = contextRead.createUnmarshaller();
            Object object = marshaller.unmarshal(new File(pathName));
            personList.add(object);
        }
        return personList;
    }
}
