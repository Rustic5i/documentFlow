package com.example.document_flow.repository.document;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.repository.abstraction.InMemoryRepository;
import com.example.document_flow.util.read.DeserializationJSON;
import com.example.document_flow.util.write.SerializableJSON;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Репозиторий для документов в формате JSON
 *
 * @param <T> тип документов
 * @author Баратов Руслан
 */
public class RepositoryJson<T extends Document> extends InMemoryRepository<T> {

    private final DeserializationJSON deserialization = new DeserializationJSON();

    private final SerializableJSON serializable = new SerializableJSON();

    private final Set<String> setPathFilJson = new HashSet<>();

    private Class<T> type;

    public RepositoryJson(Class<T> type) {
        this.type = type;
    }

    /**
     * Получить обьект из репозитория
     * Получает обьект из Json
     *
     * @param path путь к файлу Json
     * @return
     */
    @Override
    protected T getObjectFromRepository(String path) {
        return deserialization.fromJson(path, type);
    }

    /**
     * Сохранить список обьектов в формат Json
     *
     * @param objects список обьектов для сохранения
     * @return путь к сохраненным файлам
     */
    @Override
    protected Set<String> saveAllToRepository(List<T> objects) {
        String filePath = objects.get(0).getAuthor().toString() + ".json";
        setPathFilJson.add(filePath);
        Set<String> set = new HashSet<>();
        set.add(serializable.toJson(filePath, objects));
        return set;
    }

    /**
     * Сохранить обьект в репозиторий
     * Сохраняет обьект в формат Json
     *
     * @param object обьект для сохранения
     * @return путь к сохраненному файлу
     */
    @Override
    protected String saveToRepository(T object) {
        String filePath = object.getAuthor().toString() + ".json";
        setPathFilJson.add(filePath);
        return serializable.toJson(filePath, object);
    }

    /**
     * Получить список сохраненных документов
     *
     * @return список документов
     */
    @Override
    public List<T> getAll() {
        List<T> list = new ArrayList<>();
        for (String str : setPathFilJson) {
            list.addAll(deserialization.getListObjectFromJson(str, list.getClass()));
        }
        return list;
    }
}
