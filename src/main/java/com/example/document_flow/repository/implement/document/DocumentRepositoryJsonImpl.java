package com.example.document_flow.repository.implement.document;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.repository.InMemory;
import com.example.document_flow.repository.absraction.document.DocumentRepositoryJSON;
import com.example.document_flow.util.read.deserialization.abstraction.Deserialization;
import com.example.document_flow.util.read.deserialization.impement.DeserializationJSON;
import com.example.document_flow.util.write.abstraction.Serializable;
import com.example.document_flow.util.write.implement.SerializableJSON;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Репозиторий для документов в формате JSON
 *
 * @author Баратов Руслан
 */
public class DocumentRepositoryJsonImpl implements DocumentRepositoryJSON {

    private static DocumentRepositoryJsonImpl implDocumentRepositoryJson;

    private final Deserialization DESERIALIZATION = DeserializationJSON.getInstance();

    private final Serializable SERIALIZABLE = SerializableJSON.getInstance();

    private final InMemory<List<Document>> IN_MEMORY = new InMemory<>();

    private final String NAME_REPOSITORY = ".\\repositoryJson\\";

    private final Map<Document, File> fileMap = new HashMap<>();

    {
        createRepository();
    }

    private DocumentRepositoryJsonImpl() {
    }

    /**
     * @return синголтон обьект
     */
    public static DocumentRepositoryJsonImpl getInstance() {
        if (implDocumentRepositoryJson == null) {
            implDocumentRepositoryJson = new DocumentRepositoryJsonImpl();
        }
        return implDocumentRepositoryJson;
    }

    /**
     * Сохраняет объект в репозиторий, в формате Json
     * после чего сохраняет его в кеш
     *
     * @param object какой-либо объект для сохранения
     */
    @Override
    public void save(Document object) throws SaveObjectException {
        String filePath = NAME_REPOSITORY + object.getAuthor().toString() + ".json";
        File file = new File(filePath);
        SERIALIZABLE.save(file, object);
        fileMap.put(object, file);
        IN_MEMORY.save(filePath, Arrays.asList(object));
    }

    /**
     * Сохраняет список каких-либо объектов в репозиторий, в формате Json
     * после чего сохраняет объекты в кеш
     *
     * @param objects список каких-либо объектов
     */
    @Override
    public void saveAll(List<Document> objects) throws SaveObjectException {
        Map<Person, List<Document>> documentByAuthor = objects.stream().collect(Collectors.groupingBy(Document::getAuthor));
        StringBuilder filePath = new StringBuilder();
        for (Map.Entry<Person, List<Document>> value : documentByAuthor.entrySet()) {
            filePath.append(NAME_REPOSITORY + value.getKey()).append(".json");
            SERIALIZABLE.save(new File(filePath.toString()), value.getValue());
            IN_MEMORY.save(filePath.toString(), value.getValue());
            filePath.delete(0, filePath.length());
        }
    }

    /**
     * Получить все сохраненные объекты из репозитория
     *
     * @return список сохраненных объектов
     */
    @Override
    public List<Document> getAll() {
        File file = new File(NAME_REPOSITORY);
        Set<File> fileSet = Arrays.stream(file.listFiles()).collect(Collectors.toSet());
        List<Document> list = new ArrayList<>();
        fileSet.forEach(str -> list.addAll(DESERIALIZATION.get(str, list.getClass())));
        return list;
    }

    /**
     * Найти объект по id
     *
     * @param id id объекта
     * @return найденный объект
     */
    @Override
    public Optional<Document> findById(long id) {
        return Optional.of((Document) getAll().stream().filter(document -> document.getId() == id));
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
    public void update(Document object) throws SaveObjectException {
        save(object);
    }

    /**
     * Создает папку для хранения файлов Json
     */
    private void createRepository() {
        File file = new File(NAME_REPOSITORY);
        if (!file.exists()) {
            file.mkdir();
        }
    }
}
