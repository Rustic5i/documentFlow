package com.example.document_flow.repository.document;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.repository.abstraction.AbstractInMemoryDAO;
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
public class RepositoryJson<T extends Document> extends AbstractInMemoryDAO<T> {

    private final DeserializationJSON DESERIALIZATION = DeserializationJSON.getInstance();

    private final SerializableJSON SERIALIZABLE = SerializableJSON.getInstance();

    private final Set<String> setPathFilJson = new HashSet<>();

    private final Class<T> TYPE;

    public RepositoryJson(Class<T> type) {
        this.TYPE = type;
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
        return DESERIALIZATION.fromJson(path, TYPE);
    }

    /**
     * Сохранить список обьектов в формат Json
     *
     * @param objects список обьектов для сохранения
     * @return путь к сохраненным файлам
     */
    @Override
    protected Set<String> saveAllToRepository(List<T> objects) {
        List<T> value = new ArrayList<>();
        String author = objects.get(0).getAuthor().toString();
        for (T object : objects) {
            if (object.getAuthor().toString().equals(author)) {
                value.add(object);
            } else {
                setPathFilJson.addAll(saveAllToRepository(value));
            }
        }
        return setPathFilJson;
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
        return SERIALIZABLE.toJson(filePath, object);
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
            list.addAll(DESERIALIZATION.getListObjectFromJson(str, list.getClass()));
        }
        return list;
    }
}
