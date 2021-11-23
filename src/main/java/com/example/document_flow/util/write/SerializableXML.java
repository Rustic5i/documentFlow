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

    private final Logger log = LoggerFactory.getLogger(SerializableXML.class.getName());

    /**
     * Сериализует лист обьектов в формат xml
     *
     * @return Set наименования созданных файлов при сериализации
     */
    public Set<String> serializable(List<T> Objectlist) {
        Set<String> nameFills = new HashSet<>();
        for (T object : Objectlist) {
            nameFills.add(serializable(object));
        }
        return nameFills;
    }

    /**
     * Сериализует обьект в формат xml
     * @param object обьект для сериализации
     * @return наименования созданного файла при сериализации
     */
    public String serializable(T object) {
        String nameFile = object.getClass().getSimpleName() + object.getId() + ".xml";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, new File(nameFile));
        } catch (JAXBException e) {
            log.warn("Exception ", e);
        }
        return nameFile;
    }

    public boolean deleteXml(String pathName){
        File file = new File(pathName);
        return file.delete();
    }
}