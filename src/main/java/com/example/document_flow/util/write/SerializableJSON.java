package com.example.document_flow.util.write;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Класс занимается сериализацией обьектов в формат JSON
 *
 * @author Баратов Руслан
 */
public class SerializableJSON {

    private final Gson gson = new GsonBuilder().create();

    /**
     * Сериализует обьект в формат JSON
     *
     * @param nameFile имя файла
     * @param object   обьект для сериализации
     * @return путь к файлу
     * @throws IOException – если именованный файл существует, но является каталогом,
     *                     а не обычным файлом, не существует, но не может быть создан или не может быть открыт по какой-либо другой причине
     */
    public String toJson(String nameFile, Object object) {
        try (Writer writer = new FileWriter(nameFile);) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameFile;
    }

    /**
     * Сериализует список обьектов в формат JSON
     *
     * @param nameFile имя файла
     * @param objects  лист обьектов для сериализации
     * @return путь к файлу
     * @throws IOException - если именованный файл существует, но является каталогом,
     *                     а не обычным файлом, не существует, но не может быть создан или не может быть открыт по какой-либо другой причине
     */
    public String toJson(String nameFile, List<Object> objects) throws IOException {
        try (Writer writer = new FileWriter(nameFile)) {
            gson.toJson(objects, writer);
        }
        return nameFile;
    }
}