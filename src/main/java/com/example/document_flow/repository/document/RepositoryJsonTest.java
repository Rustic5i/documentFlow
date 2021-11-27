package com.example.document_flow.repository.document;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.repository.InMemory;
import com.example.document_flow.repository.abstraction.DAO.DAO;
import com.example.document_flow.util.read.DeserializationJSON;
import com.example.document_flow.util.write.SerializableJSON;

import java.util.*;
import java.util.stream.Collectors;

public class RepositoryJsonTest implements DAO<Document> {

    private final DeserializationJSON deserialization = DeserializationJSON.getInstance();

    private final SerializableJSON serializable = SerializableJSON.getInstance();

    private final InMemory<List<Document>> inMemory = new InMemory<>();

    private final Set<String> setPathFilJson = new HashSet<>();

    @Override
    public void save(Document object) {
        String filePath = object.getAuthor().toString() + ".json";
        serializable.toJson(filePath, object);
        setPathFilJson.add(filePath);
        inMemory.save(filePath, Arrays.asList(object));
    }

    @Override
    public void saveAll(List<Document> objects) {
        Map<Person, List<Document>> documentByAuthor = objects.stream().collect(Collectors.groupingBy(Document::getAuthor));
        StringBuilder author = new StringBuilder();
        documentByAuthor.entrySet().stream().forEach(value -> {
            author.append(value.getKey()).append(".json");
            serializable.toJson(author.toString(), value.getValue());
            setPathFilJson.add(author.toString());
            inMemory.save(author.toString(), value.getValue());
            author.delete(0, author.length());
        });
    }

    @Override
    public List<Document> getAll() {
        List<Document> list = new ArrayList<>();
        for (String str : setPathFilJson) {
            list.addAll(deserialization.getListObjectFromJson(str, list.getClass()));
        }
        return list;
    }
}
