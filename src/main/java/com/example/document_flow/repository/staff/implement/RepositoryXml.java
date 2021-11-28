package com.example.document_flow.repository.staff.implement;

import com.example.document_flow.entity.staff.Staff;
import com.example.document_flow.repository.InMemory;
import com.example.document_flow.repository.DAO.DAO;
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
public class RepositoryXml<T extends Staff> implements DAO<T> {

    private final SerializableXML<T> SERIALIZABLE = SerializableXML.getInstance();

    private final DeserializationXML<T> DESERIALIZATION = DeserializationXML.getInstance();

    private final Set<String> setPathFilXml = new HashSet<>();

    private final InMemory<T> inMemory = new InMemory<>();

    private final Class<T> TYPE;

    public RepositoryXml(Class<T> type) {
        this.TYPE = type;
    }

    /**
     * Сохраняет какой-либо объект в формат xml
     *
     * @param object объект для сохранения
     * @return путь к файлу
     */
    @Override
    public void save(T object) {
        String pathFile = SERIALIZABLE.toXML(object);
        setPathFilXml.add(pathFile);
        inMemory.save(pathFile, object);
    }

    /**
     * Сохраняет список обьектов в формат Xml
     *
     * @param objects список объектов
     * @return пути к файлу
     */
    @Override
    public void saveAll(List<T> objects) {
        objects.forEach(this::save);
    }

    /**
     * Получить все сохраненные объекты
     *
     * @return список каких-либо объектов
     */
    @Override
    public List<T> getAll() {
        return DESERIALIZATION.fromXMl(setPathFilXml, TYPE);
    }

}
