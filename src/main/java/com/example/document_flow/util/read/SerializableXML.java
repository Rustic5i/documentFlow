package com.example.document_flow.util.read;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.factory.generator.DataGenerator;
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
 */
public class SerializableXML {

    private List<Person> personList = DataGenerator.getInstance().personList.stream().limit(3).toList();

    private final Logger log = LoggerFactory.getLogger(SerializableXML.class.getName());

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

                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(person, new File(nameFile));
                nameFills.add(nameFile);
            } catch (JAXBException e) {
                log.warn("Exception ", e);
            }
        }
        return nameFills;
    }
}