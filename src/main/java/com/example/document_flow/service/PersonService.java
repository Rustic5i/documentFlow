package com.example.document_flow.service;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.util.read.DeserializationXML;
import com.example.document_flow.util.write.SerializableXML;

import java.util.HashMap;
import java.util.List;

public class PersonService {

    private final HashMap<String, Person> personHashMap = new HashMap<>();

    private final SerializableXML<Person> serializable = new SerializableXML();

    private final DeserializationXML<Person> deserialization = new DeserializationXML<>();

    public void save(Person person) {
        String filePath = serializable.serializable(person);
        personHashMap.put(filePath, person);
    }

    public void saveAll(List<Person> personList) {
        for (Person person : personList) {
            save(person);
        }
    }

    public Person getPerson(String pathName) {
        if (personHashMap.containsKey(pathName)) {
            return personHashMap.get(pathName);
        }
        return deserialization.deserialization(pathName, Person.class);
    }

    public boolean deletePerson(String pathName){
        return serializable.deleteXml(pathName);
    }
}
