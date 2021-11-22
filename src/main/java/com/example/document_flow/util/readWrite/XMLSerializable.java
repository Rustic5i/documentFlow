package com.example.document_flow.util.readWrite;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.factory.generator.DataGenerator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Это класс занимается сериализацией обьектов в формат xml
 */
public class XMLSerializable {

    private List<Person> personList = DataGenerator.getInstance().personList.stream().limit(3).toList();

    /**
     * Сериализует три случайных обьекта <code>Person</code> в формат xml
     * @return Set наименования созданных файлов при сериализации
     */
    public Set<String> serializableXmlStaff() {
        Set<String> nameFills = new HashSet<>();
        for (Person person : personList) {
            try {
                String nameFile = "person" + person.getId() + ".xml";
                JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);

                Marshaller marshaller1 = jaxbContext.createMarshaller();
                marshaller1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller1.marshal(person, new File(nameFile));
                nameFills.add(nameFile);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        return nameFills;
    }
}