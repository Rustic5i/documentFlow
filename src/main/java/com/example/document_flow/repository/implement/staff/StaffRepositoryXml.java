package com.example.document_flow.repository.implement.staff;

import com.example.document_flow.entity.staff.Staff;
import com.example.document_flow.repository.InMemory;
import com.example.document_flow.repository.absraction.Repository;
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
public class StaffRepositoryXml<T extends Staff> implements Repository<T> {

    private final SerializableXML<T> SERIALIZABLE = SerializableXML.getInstance();

    private final DeserializationXML<T> DESERIALIZATION = DeserializationXML.getInstance();

    private final Set<String> SET_PATH_FILE_XML = new HashSet<>();

    private final InMemory<T> IN_MEMORY = new InMemory<>();

    private final Class<T> TYPE;

    public StaffRepositoryXml(Class<T> type) {
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
        SET_PATH_FILE_XML.add(pathFile);
        IN_MEMORY.save(pathFile, object);
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
        return DESERIALIZATION.fromXMl(SET_PATH_FILE_XML, TYPE);
    }

    /**
     * Искать сохраненный объект по id
     *
     * @param id id объекта
     * @return найденный объект
     */
    @Override
    public T findById(long id) {
        return (T) getAll().stream().filter(object -> object.getId() == id);
    }
}
