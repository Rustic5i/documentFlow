package com.example.document_flow.repository.document;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.repository.InMemory;
import com.example.document_flow.repository.abstraction.DAO.DAO;
import com.example.document_flow.util.read.DeserializationJSON;
import com.example.document_flow.util.write.SerializableJSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RepositoryJsonTest implements DAO<Document> {

    private final DeserializationJSON DESERIALIZATION = DeserializationJSON.getInstance();

    private final SerializableJSON SERIALIZABLE = SerializableJSON.getInstance();

    private final InMemory<List<Document>> inMemory = new InMemory<>();

    private final Set<String> setPathFilJson = new HashSet<>();

    @Override
    public void save(Document object) {
        String filePath = object.getAuthor().toString() + ".json";
        SERIALIZABLE.toJson(filePath, object);
        setPathFilJson.add(filePath);
        inMemory.save(filePath, Arrays.asList(object));
    }

    @Override
    public void saveAll(List<Document> objects) {
        Map<Person, List<Document>> documentByAuthor = objects.stream().collect(Collectors.groupingBy(Document::getAuthor));
        StringBuilder author = new StringBuilder();
        documentByAuthor.entrySet().stream().forEach(value -> {
            author.append(value.getKey()).append(".json");
            SERIALIZABLE.toJson(author.toString(), value.getValue());
            setPathFilJson.add(author.toString());
            inMemory.save(author.toString(), value.getValue());
            author.delete(0, author.length());
        });
    }

    @Override
    public List<Document> getAll() {
        List<Document> list = new ArrayList<>();
        setPathFilJson.stream()
                .forEach(str -> list.addAll(DESERIALIZATION.getListObjectFromJson(str, list.getClass())));
        return list;
    }
}
