package com.example.document_flow.repository.implement.staff;

import com.example.document_flow.entity.staff.Staff;
import com.example.document_flow.repository.InMemory;
import com.example.document_flow.repository.absraction.Repository;
import com.example.document_flow.util.read.deserialization.abstraction.Deserialization;
import com.example.document_flow.util.read.deserialization.impement.DeserializationXML;
import com.example.document_flow.util.write.abstraction.Serializable;
import com.example.document_flow.util.write.implement.SerializableXML;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Репозиторий для “Элементов организационных структур” в формате Xml
 *
 * @param <T> тип "организационных структур"
 * @author Баратов Руслан
 */
public class StaffRepositoryXml<T extends Staff> implements Repository<T> {

    private final Serializable SERIALIZABLE = SerializableXML.getInstance();

    private final Deserialization DESERIALIZATION = DeserializationXML.getInstance();

    private final InMemory<T> IN_MEMORY = new InMemory<>();

    private final Class<T> TYPE;

    private final String NAME_REPOSITORY = ".\\repositoryXml\\";

    {
        createRepository();
    }

    private void createRepository() {
        File file = new File(NAME_REPOSITORY);
        if (!file.exists()) {
            file.mkdir();
        }
    }

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
        String pathFile = NAME_REPOSITORY + object.getId() + ".xml";
        SERIALIZABLE.save(new File(pathFile), object);
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
        File file = new File(NAME_REPOSITORY);
        Set<File> fileSet = Arrays.stream(file.listFiles()).collect(Collectors.toSet());
        return DESERIALIZATION.getList(fileSet, TYPE);
    }

    /**
     * Искать сохраненный объект по id
     *
     * @param id id объекта
     * @return найденный объект
     */
    @Override
    public Optional<T> findById(long id) {
        return Optional.of((T) getAll().stream().filter(object -> object.getId() == id));
    }
}
