package com.example.document_flow.repository.staff.implement;

import com.example.document_flow.entity.staff.Staff;
import com.example.document_flow.repository.InMemory;
import com.example.document_flow.repository.abstraction.DAO.DAO;
import com.example.document_flow.util.read.DeserializationXML;
import com.example.document_flow.util.write.SerializableXML;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepositoryXmlTest<T extends Staff> implements DAO<T> {

    private final SerializableXML<T> serializable = SerializableXML.getInstance();

    private final DeserializationXML<T> deserialization = DeserializationXML.getInstance();

    private final Set<String> setPathFilXml = new HashSet<>();

    private final InMemory<T> inMemory = new InMemory<>();

    private Class<T> type;

    public RepositoryXmlTest(Class<T> type) {
        this.type = type;
    }

    @Override
    public void save(T object) {
        String pathFile = serializable.toXML(object);
        setPathFilXml.add(pathFile);
        inMemory.save(pathFile, object);
    }

    @Override
    public void saveAll(List<T> objects) {
        objects.forEach(this::save);
    }

    @Override
    public List<T> getAll() {
        return deserialization.fromXMl(setPathFilXml, type);
    }

}
