package com.example.document_flow.util.read.abstraction;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * Список общих методов для десериализации объектов
 *
 * @author Баратов Руслан
 */
public interface Deserialization {

    /**
     * Десериализует файл в объект(ы)
     *
     * @param filePath путь к файлу
     * @param type     к какому типу маппить обьект
     * @param <T>      тип десериализованного обьекта
     * @return десериализованный обьект(ы)
     */
    <T> T get(File filePath, Class<T> type);

    /**
     * Десериализует список файлов в объекты
     *
     * @param filePaths список расположений/пути к файлу
     * @param type      тип класса для маппинга
     * @param <T>       к какому типу мапить обьекты
     * @return список десериализуемых обьектов
     */
    <T> List<T> getList(Set<File> filePaths, Class<T> type);
}
