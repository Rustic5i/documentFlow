package com.example.document_flow.util.read;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Класс специализируется на считывание файла в формате sql, расположенные в папке ресурсов(resources)
 *
 * @author Баратов Руслан
 */
public class ReadFileSql {

    private final String RESOURCE_PATH = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(""),
            "Папка ресурсов не найдено").getPath();

    /**
     * Читать файл sql
     *
     * @param filePath расположение файла sql в папке ресурсов (resources)
     * @return список sql скриптов
     */
    public List<String> read(String filePath) {
        List<String> sqlScript = new ArrayList<>();
        String delimiter = ";";
        try {
            Scanner scanner = new Scanner(new File(RESOURCE_PATH + filePath)).useDelimiter(delimiter);
            while (scanner.hasNext()) {
                sqlScript.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sqlScript;
    }

}
