package com.example.document_flow.util.read;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * Класс обертка над классом <code>Properties</code>
 *
 * @author Баратов Руслан
 */
public class ReadFileProperties {

    private final Properties PROPERTIES = new Properties();

    private final String RESOURCE_PATH = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(""),
            "Папка ресурсов не найдено").getPath();

    /**
     * Считывает список свойств (пары ключей и элементов) из файла формата properties
     *
     * @param propertiesPath расположения файла в папке resources
     */
    public synchronized void load(String propertiesPath) {
        try {
            PROPERTIES.load(new FileInputStream(RESOURCE_PATH + propertiesPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Выполняет поиск свойства с указанным ключом в списке свойств
     *
     * @param key - ключ свойства
     * @return значение из списка свойств
     */
    public String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
