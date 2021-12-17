package com.example.document_flow.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.Properties;

/**
 * Класс обертка над классом <code>Properties</code>
 *
 * @author Баратов Руслан
 */
public class ReadFileProperties {

    private static final String RESOURCE_PATH = "config/config.properties";

    private final Properties PROPERTIES = new Properties();

    private final Logger LOGGER = LoggerFactory.getLogger(ReadFileProperties.class.getName());

    public ReadFileProperties() {
        load();
    }

    /**
     * Считывает список свойств (пары ключей и элементов) из файла формата properties
     */
    private synchronized void load() {
        try (InputStream INPUT_STREAM_PROPERTIES_FILE = Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(RESOURCE_PATH),
                MessageFormat.format("Папка ресурсов {0} не найдено",RESOURCE_PATH))) {
            PROPERTIES.load(INPUT_STREAM_PROPERTIES_FILE);
        } catch (IOException e) {
            LOGGER.error("Ошибка при считывание данных из файла Properties", e);
            System.exit(1);
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
