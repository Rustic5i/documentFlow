package com.example.document_flow.repository.implement.staff;

import com.example.document_flow.entity.staff.Staff;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.repository.InMemory;
import com.example.document_flow.repository.absraction.Repository;
import com.example.document_flow.util.read.deserialization.abstraction.Deserialization;
import com.example.document_flow.util.read.deserialization.impement.DeserializationXML;
import com.example.document_flow.util.write.abstraction.Serializable;
import com.example.document_flow.util.write.implement.SerializableXML;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private final Map<T, File> fileMap = new HashMap<>();

    private final Class<T> TYPE;

    private File file;

    /**
     * Создает репозиторий
     *
     * @param filePath путь/наименования репозитория
     */
    public void createRepository(File filePath) {
        this.file = filePath;
        if (!filePath.exists()) {
            filePath.mkdir();
        }
    }

    public StaffRepositoryXml(Class<T> type) {
        this.TYPE = type;
    }

    /**
     * Сохраняет какой-либо объект в формат xml
     *
     * @param object объект для сохранения
     */
    @Override
    public void save(T object) throws SaveObjectException {
        String pathFile = file.getPath() + "\\" + object.getId() + ".xml";
        File file = new File(pathFile);
        SERIALIZABLE.save(file, object);
        fileMap.put(object, file);
        IN_MEMORY.save(pathFile, object);
    }

    /**
     * Сохраняет список объектов в формат Xml
     *
     * @param objects список объектов
     */
    @Override
    public void saveAll(List<T> objects) throws SaveObjectException {
        for (T staff : objects) {
            save(staff);
        }
    }

    /**
     * Получить все сохраненные объекты
     *
     * @return список каких-либо объектов
     */
    @Override
    public List<T> getAll() {
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

    /**
     * Удалить объект по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        fileMap.keySet().stream().filter(obj -> obj.getId() == id).forEach(obj -> {
            fileMap.get(obj).delete();
            fileMap.remove(obj);
        });
    }

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(T object) throws SaveObjectException {
        save(object);
    }
}
