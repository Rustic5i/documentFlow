package com.example.document_flow.util.write.implement;

import com.example.document_flow.entity.staff.Staff;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.util.write.abstraction.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Это класс занимается сериализацией объектов в формат xml
 *
 * @author Баратов Руслан
 */
public class SerializableXML<T extends Staff> implements Serializable {

    private final Logger LOGGER = LoggerFactory.getLogger(SerializableXML.class.getName());

    private static SerializableXML serializableXML;

    private SerializableXML() {
    }

    /**
     * Сохраняет объект в файл
     *
     * @param filePath путь - куда сохранять обьект
     * @param object   объект для сохранения
     * @return путь к сохраненному объекту
     * @throws SaveObjectException когда сохранения объекта терпит не удачу по какой-либо причине.
     * Основная причины если при создании объекта маршаллера возникла ошибка или Файл Xml не существует, или не может быть создан.
     */
    @Override
    public Path save(File filePath, Object object) throws SaveObjectException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, filePath);
        } catch (JAXBException e) {
            throw new SaveObjectException("Файл Xml не существует, или не может быть создан " + e);
        }
        return filePath.toPath();
    }

    /**
     * Сохраняет список обьектов в список файлов
     *
     * @param filePaths маппа обьектов. Где ключ - директория и наименования файла.
     *                  Где значение - сам объект для десериализации
     * @param <T>       тип объектов для сериализации
     * @return список расположения сохраненных файлов
     * @throws SaveObjectException когда сохранения объекта терпит не удачу по какой-либо причине.
     * Основная причины если при создании объекта маршаллера возникла ошибка или Файл Xml не существует, или не может быть создан.
     */
    @Override
    public <T> Set<Path> saveAll(Map<File, T> filePaths) throws SaveObjectException {
        Set<Path> filesPath = new HashSet<>();
        for (File key : filePaths.keySet()) {
            save(key, filePaths.get(key));
        }
        return filesPath;
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