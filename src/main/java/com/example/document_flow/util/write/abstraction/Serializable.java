package com.example.document_flow.util.write.abstraction;

import com.example.document_flow.exception.SaveObjectException;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

/**
 * Список общих методов для сериализации объектов
 *
 * @author Баратов Руслан
 */
public interface Serializable {

    /**
     * Сохраняет объект в файл
     *
     * @param filePath путь - куда сохранять обьект
     * @param object   объект для сохранения
     * @return путь к сохраненному объекту
     */
    Path save(File filePath, Object object) throws SaveObjectException;

    /**
     * Сохраняет список обьектов в список файлов
     *
     * @param filePaths маппа обьектов. Где ключ - директория и наименования файла.
     *                  Где значение - сам объект для десериализации
     * @param <T>       тип объектов для сериализации
     * @return список расположения сохраненных файлов
     */
    <T> Set<Path> saveAll(Map<File, T> filePaths)throws SaveObjectException;
}
