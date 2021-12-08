package com.example.document_flow.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ReadFileSql {

    private final String RESOURCE_PATH = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(""),
            "Папка ресурсов не найдено").getPath();

    public List<String> read(String filePath) {
        List<String> sqlScript = new ArrayList<>();
        String delimiter = ";";
        try {
            Scanner scanner = new Scanner(new File(RESOURCE_PATH+filePath)).useDelimiter(";");
            while (scanner.hasNext()) {
                sqlScript.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sqlScript;
    }

}
