package com.example.document_flow.repository.staff.implement;

import com.example.document_flow.entity.staff.Staff;
import com.example.document_flow.repository.abstraction.AbstractInMemoryDAO;
import com.example.document_flow.util.read.DeserializationXML;
import com.example.document_flow.util.write.SerializableXML;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Репозиторий для “Элементов организационных структур” в формате Xml
 *
 * @param <T> тип "организационных структур"
 * @author Баратов Руслан
 */
public class RepositoryXml<T extends Staff> extends AbstractInMemoryDAO<T> {

    private final SerializableXML<T> serializable = SerializableXML.getInstance();

    private final DeserializationXML<T> deserialization = DeserializationXML.getInstance();

    private final Set<String> setPathFilXml = new HashSet<>();

    private Class<T> type;

    public RepositoryXml(Class<T> type) {
        this.type = type;
    }

    /**
     * Получает обьект из Xml
     *
     * @param path путь к файлу
     * @return какой-либо обьект
     */
    @Override
    protected T getObjectFromRepository(String path) {
        return deserialization.fromXMl(path, type);
    }

    /**
     * Сохраняет список обьектов в формат Xml
     *
     * @param objects список обьектов
     * @return пути к файлу
     */
    @Override
    protected Set<String> saveAllToRepository(List<T> objects) {
        for (T object : objects) {
            setPathFilXml.add(saveToRepository(object));
        }
        return setPathFilXml;
    }

    /**
     * Сохроняет какой-либо обьект в формат xml
     *
     * @param object обьект для сохранения
     * @return путь к файлу
     */
    @Override
    protected String saveToRepository(T object) {
        String pathFile = serializable.toXML(object);
        setPathFilXml.add(pathFile);
        return pathFile;
    }

    /**
     * Получить все сохраненные обьекты
     *
     * @return списко каких-либо обьектов
     */
    @Override
    public List<T> getAll() {
        return deserialization.fromXMl(setPathFilXml, type);
    }
}
