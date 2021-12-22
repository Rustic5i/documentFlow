package com.example.document_flow.util.write.implement;

import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.util.write.abstraction.Serializable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Класс занимается сериализацией объектов в формат JSON
 *
 * @author Баратов Руслан
 */
public class SerializableJSON implements Serializable {

    private static SerializableJSON serializableJSON;

    private final Gson gson = new GsonBuilder().create();

    private SerializableJSON() {
    }

    /**
     * @return синголтон обьект
     */
    public static SerializableJSON getInstance() {
        if (serializableJSON == null) {
            serializableJSON = new SerializableJSON();
        }
        return serializableJSON;
    }

    /**
     * Сериализует объект(ы) в формат JSON
     *
     * @param filePath путь к директории и наименования файла
     * @param object   объект для сериализации
     * @return путь к сохраненному объекту
     * @throws SaveObjectException когда сохранения объекта терпит не удачу по какой-либо причине
     */
    @Override
    public Path save(File filePath, Object object) throws SaveObjectException {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            throw new SaveObjectException("Файл Json не существует, или не может быть создан ", e);
        }
        return filePath.toPath();
    }

    /**
     * Сохраняет список объектов в список файлов
     *
     * @param filePaths маппа объектов. Где ключ - директория и наименования файла.
     *                  Где значение - сам объект для десериализации
     * @param <T>       тип объектов для сериализации
     * @return список расположения сохраненных файлов
     * @throws SaveObjectException когда сохранения объекта терпит не удачу по какой-либо причине
     */
    @Override
    public <T> Set<Path> saveAll(Map<File, T> filePaths) throws SaveObjectException {
        Set<Path> filesPath = new HashSet<>();
        for (File key : filePaths.keySet()) {
            save(key, filePaths.get(key));
        }
        return filesPath;
    }
}