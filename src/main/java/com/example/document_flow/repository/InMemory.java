package com.example.document_flow.repository;

import java.util.WeakHashMap;

/**
 * Хранилище в памяти.
 * Хранит в памяти объекты только с сильными ссылками.
 * Объект со слабой ссылкой будут автоматический удалены сборщиком мусора
 *
 * @param <T> тип объектов для хранения
 * @author Баратов Руслан
 */
public class InMemory<T> {

    private final WeakHashMap<String, T> objectWeakHashMap = new WeakHashMap<>();

    /**
     * Сохраняет объект в кэш
     *
     * @param object какой-либо объект для сохранения
     */
    public void save(String path, T object) {
        objectWeakHashMap.put(path, object);
    }

    /**
     * Получить объект из кэша
     *
     * @param path путь к файлу
     * @return какой-либо объект
     */
    public T getObject(String path) {
        return objectWeakHashMap.get(path);
    }

    /**
     * Проверяет, храниться ли объект в кэше
     * @param path путь к файлу
     * @return если объект есть в кэше возвращает true, если нет false
     */
    public boolean containsInMemory(String path) {
        return objectWeakHashMap.containsKey(path);
    }
}
