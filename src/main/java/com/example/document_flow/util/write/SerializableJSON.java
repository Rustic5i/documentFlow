package com.example.document_flow.util.write;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.entity.staff.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Этот класс занимается сериализацией обьектов в формат JSON
 *
 * @author Баратов Руслан
 */
public class SerializableJSON {

    /**
     * Сериализует список сгруппирированых документов по автору в формат JSON
     *
     * @param personListMap - список документов для сериализации
     * @return Set наименования созданных файлов при сериализации
     * @throws IOException – если именованный файл существует, но является каталогом,
     *                     а не обычным файлом, не существует, но не может быть создан или не может быть открыт по какой-либо другой причине
     */
    public Set<String> Serialization(Map<Person, List<Document>> personListMap) throws IOException {
        Set<String> nameFills = new HashSet<>();
        for (Map.Entry<Person, List<Document>> entry : personListMap.entrySet()) {
            String nameFile = entry.getKey().toString() + ".json";

            Writer writer = new FileWriter(nameFile);
            Gson gson = new GsonBuilder().create();
            gson.toJson(entry.getValue(), writer);
            nameFills.add(nameFile);
            writer.close();
        }
        return nameFills;
    }
}