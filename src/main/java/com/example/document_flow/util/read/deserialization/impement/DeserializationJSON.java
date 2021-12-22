package com.example.document_flow.util.read.deserialization.impement;

import com.example.document_flow.util.read.deserialization.abstraction.Deserialization;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.Primitives;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Занимается десериализацией обьектов из формата JSON
 *
 * @author Баратов Руслан
 */
public class DeserializationJSON implements Deserialization {

    private static DeserializationJSON deserializationJSON;

    private final Gson gson = new GsonBuilder().create();

    private final Logger logger = LoggerFactory.getLogger(DeserializationJSON.class.getName());

    private DeserializationJSON() {
    }

    /**
     * @return синголтон обьект
     */
    public static DeserializationJSON getInstance() {
        if (deserializationJSON == null) {
            deserializationJSON = new DeserializationJSON();
        }
        return deserializationJSON;
    }

    /**
     * Десериализует файл в объект(ы)
     *
     * @param filePath путь к файлу
     * @param type     к какому типу маппить обьект
     * @param <T>      тип десериализованного обьекта
     * @return десериализованный обьект(ы)
     */
    @Override
    public <T> T get(File filePath, Class<T> type) {
        String stringJSON = readJson(filePath.getPath());
        Object object = gson.fromJson(stringJSON, type);
        return Primitives.wrap(type).cast(object);
    }

    /**
     * Десериализует список файлов в обьекты
     *
     * @param filePaths список расположений/пути к файлу
     * @param type      тип класса для маппинга
     * @param <T>       к какому типу мапить обьекты
     * @return список десериализуемых обьектов
     */
    @Override
    public <T> List<T> getList(Set<File> filePaths, Class<T> type) {
        List<T> objectList = new ArrayList<>();
        filePaths.forEach(str -> objectList.add(get(str, type)));
        return objectList;
    }

    /**
     * Читает файл Json
     *
     * @param filePath путь к файлу
     * @return JSON  в формате String
     */
    private String readJson(String filePath) {
        String line;
        StringBuilder stringJSON = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            while ((line = br.readLine()) != null) {
                stringJSON.append(line);
            }
        } catch (IOException e) {
            logger.warn("Ошибка ввода-вывода ", e);
        }
        return stringJSON.toString();
    }
}
