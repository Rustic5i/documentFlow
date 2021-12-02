package com.example.document_flow.repository.document;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.repository.InMemory;
import com.example.document_flow.repository.DAO.DAO;
import com.example.document_flow.util.read.DeserializationJSON;
import com.example.document_flow.util.write.SerializableJSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Репозиторий для документов в формате JSON
 *
 * @author Баратов Руслан
 */
public class DocumentRepositoryJson implements DAO<Document> {

    private final DeserializationJSON DESERIALIZATION = DeserializationJSON.getInstance();

    private final SerializableJSON SERIALIZABLE = SerializableJSON.getInstance();

    private final InMemory<List<Document>> IN_MEMORY = new InMemory<>();

    private final Set<String> SET_PATH_FILE_JSON = new HashSet<>();

    /**
     * Сохраняет обьект в репозиторий, в формате Json
     * после чего сохраняет его в кеш
     *
     * @param object какой-либо обьект для сохранения
     */
    @Override
    public void save(Document object) {
        String filePath = object.getAuthor().toString() + ".json";
        SERIALIZABLE.toJson(filePath, object);
        SET_PATH_FILE_JSON.add(filePath);
        IN_MEMORY.save(filePath, Arrays.asList(object));
    }

    /**
     * Сохраняет список каких-либо объектов в репозиторий, в формате Json
     * после чего сохраняет объекты в кеш
     *
     * @param objects список каких-либо объектов
     */
    @Override
    public void saveAll(List<Document> objects) {
        Map<Person, List<Document>> documentByAuthor = objects.stream().collect(Collectors.groupingBy(Document::getAuthor));
        StringBuilder author = new StringBuilder();
        documentByAuthor.entrySet().stream().forEach(value -> {
            author.append(value.getKey()).append(".json");
            SERIALIZABLE.toJson(author.toString(), value.getValue());
            SET_PATH_FILE_JSON.add(author.toString());
            IN_MEMORY.save(author.toString(), value.getValue());
            author.delete(0, author.length());
        });
    }

    /**
     * Получить все сохраненные объекты из репозитория
     *
     * @return список сохраненных объектов
     */
    @Override
    public List<Document> getAll() {
        List<Document> list = new ArrayList<>();
        SET_PATH_FILE_JSON.stream()
                .forEach(str -> list.addAll(DESERIALIZATION.getListObjectFromJson(str, list.getClass())));
        return list;
    }
}
