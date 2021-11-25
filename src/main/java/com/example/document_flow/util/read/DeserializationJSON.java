package com.example.document_flow.util.read;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.Primitives;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Занимается десериализацией обьектов из формата JSON
 *
 * @author Баратов Руслан
 */
public class DeserializationJSON {

    private final Gson gson = new GsonBuilder().create();

    private final Logger log = LoggerFactory.getLogger(DeserializationJSON.class.getName());

    /**
     * Десериализует файл в обьект
     *
     * @param nameFile имя файла
     * @param type     к какому типу маппить обьект
     * @param <T>      тип десериализованного обьекта
     * @return десериализованный обьект
     */
    public <T> T fromJson(String nameFile, Class<T> type) {
        Object object = gson.fromJson(nameFile, type);
        return Primitives.wrap(type).cast(object);
    }

    /**
     * Десериализует файл в массив обьектов
     *
     * @param nameFile имя файла
     * @param type     к какому типу маппить обьект
     * @param <T>      тип десериализованного обьекта
     * @return массив обьектов
     */
    public <T> T getListObjectFromJson(String nameFile, Class<T> type) {
        String stringJSON = readJson(nameFile);
        Object object = gson.fromJson(stringJSON, type);
        return Primitives.wrap(type).cast(object);
    }

    /**
     * Читает файл Json
     *
     * @param filePath путь к файлу
     * @return JSON  в формате String
     */
    private String readJson(String filePath) {
        String line = null;
        StringBuilder stringJSON = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            while ((line = br.readLine()) != null) {
                stringJSON.append(line);
            }
        } catch (IOException e) {
            log.warn("Ошибка ввода-вывода ", e);
        }
        return stringJSON.toString();
    }
}
