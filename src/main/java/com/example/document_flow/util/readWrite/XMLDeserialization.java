package com.example.document_flow.util.readWrite;

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
public class XMLDeserialization {

    /**
     *
     * @param pathName наименования файла с форматом xml для десериализации обьекта
     * @return Десериализованный обьект <code>Person</code>
     * @throws JAXBException – если при создании JAXBContext произошла ошибка
     *  Unmarshalexception – Если обработчик ValidationEventHandler возвращает false из своего метода handleEvent
     *  или обработчик не может выполнить привязку XML к Java.
     *  IllegalArgumentException – Если параметр файла равен нулю
     */
    public Person deserializationFromXml(String pathName) throws JAXBException {
        JAXBContext contextRead = JAXBContext.newInstance(Person.class);

        Unmarshaller unmarshaller = contextRead.createUnmarshaller();
        Person person = (Person) unmarshaller.unmarshal(new File(pathName));
        return person;
    }

    /**
     * Десериализует список документов в обьект <code>Person</code>
     * @param pathNames список наименнований документов в формате XML для сериализации
     * @return список сериализованных документов
     * @throws JAXBException – если при создании JAXBContext произошла ошибка
     *  Unmarshalexception – Если обработчик ValidationEventHandler возвращает false из своего метода handleEvent
     *  или обработчик не может выполнить привязку XML к Java.
     *  IllegalArgumentException – Если параметр файла равен нулю
     */
    public List<Person> deserializationFromXml(Set<String> pathNames) throws JAXBException {
        List<Person> personList = new ArrayList<>();
        for (String pathName : pathNames) {
            JAXBContext contextRead =JAXBContext.newInstance(Person.class);
            Unmarshaller unmarshaller = contextRead.createUnmarshaller();
            Person person = (Person) unmarshaller.unmarshal(new File(pathName));
            personList.add(person);
        }
        return personList;
    }
}
