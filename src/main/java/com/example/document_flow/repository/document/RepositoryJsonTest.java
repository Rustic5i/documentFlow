package com.example.document_flow.repository.document;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.repository.InMemory;
import com.example.document_flow.repository.abstraction.DAO.DAO;
import com.example.document_flow.util.read.DeserializationJSON;
import com.example.document_flow.util.write.SerializableJSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepositoryJsonTest implements DAO<Document> {

    private final DeserializationJSON deserialization = DeserializationJSON.getInstance();

    private final SerializableJSON serializable = SerializableJSON.getInstance();

    private final InMemory<List<Document>> inMemory = new InMemory<>();

    private final Set<String> setPathFilJson = new HashSet<>();

    @Override
    public void save(Document object) {
        String filePath = object.getAuthor().toString() + ".json";
        serializable.toJson(filePath,object);
        setPathFilJson.add(filePath);
        inMemory.save(filePath, Arrays.asList(object));
    }

    @Override
    public void saveAll(List<Document> objects) {  ///Отсановился тут. Тут баг !!!
        String filePath = objects.get(0).getAuthor().toString() + ".json";
        serializable.toJson(filePath,objects);
        setPathFilJson.add(filePath);
        inMemory.save(filePath,objects);
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
