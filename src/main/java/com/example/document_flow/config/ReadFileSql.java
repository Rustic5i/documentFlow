package com.example.document_flow.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Класс специализируется на считывание файла в формате sql, расположенные в папке ресурсов(resources)
 *
 * @author Баратов Руслан
 */
public class ReadFileSql {

    private static final String RESOURCE_PATH = "import.sql";

    private final InputStream INPUT_STREAM_SQL_FILE = Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(RESOURCE_PATH),
            "Папка ресурсов " + RESOURCE_PATH + " не найдено");

    /**
     * Читать файл sql
     *
     * @return список sql скриптов
     * @throws IOException При возникновении ошибки ввода-вывода
     */
    public List<String> read() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(INPUT_STREAM_SQL_FILE))) {
            String delimiter = ";";
            return Arrays.stream(reader.lines().reduce((acc, x) -> acc + x).get().split(delimiter)).toList();
        }
    }
}
