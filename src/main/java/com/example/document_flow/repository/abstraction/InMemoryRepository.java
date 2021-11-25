package com.example.document_flow.repository.abstraction;

import com.example.document_flow.repository.abstraction.DAO.DAO;

import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * Хранилище в памяти.
 * Хранит в памяти обьекты только с сильными ссылками.
 * Обьект со слабой ссылкой будут автоматический удалены сборщиком мусора
 *
 * @param <T> тип обьектов для хранения
 * @author Баратов Руслан
 */
public abstract class InMemoryRepository<T> implements DAO<T> {

    private WeakHashMap<String, Object> objectWeakHashMap = new WeakHashMap<>();

    /**
     * Сохраняет обьект в репозиторий,
     * после чего сохраняет его в кеш
     *
     * @param object какой-либо обьект для сохранения
     */
    @Override
    public void save(T object) {
        String path = saveToRepository(object);
        objectWeakHashMap.put(path, object);
    }

    /**
     * Сохраняет список каких-либо обьектов в репозиторий,
     * после чего сохраняет обьекты в кеш
     *
     * @param objects список каких-либо обьектов
     */
    @Override
    public void saveAll(List<T> objects) {
        Set<String> path = saveAllToRepository(objects);
        for (String str : path) {
            objectWeakHashMap.put(str, objects);
        }
    }

    /**
     * Получает обьект из какого либо файла
     *
     * @param path путь к файлу
     * @return какой-либо обьект
     */
    public Object getObject(String path) {
        if (objectWeakHashMap.containsKey(path)) {
            return objectWeakHashMap.get(path);
        } else {
            return getObjectFromRepository(path);
        }
    }

    /**
     * Получить обьект из какого-либо репозитория
     *
     * @param path путь к файлу
     * @return
     */
    protected abstract T getObjectFromRepository(String path);

    /**
     * Сохранить список обьектов в репозиторий
     *
     * @param objects список обьектов для сохранения
     * @return список путей к файлу
     */
    protected abstract Set<String> saveAllToRepository(List<T> objects);

    /**
     * Сохраняет обьект в репозиторий
     *
     * @param object обьект для сохранения
     * @return путь к файлу
     */
    protected abstract String saveToRepository(T object);

    /**
     * Получить все сохраненные обьекты из репозитория
     *
     * @return список сохраненных обьектов
     */
    public abstract List<T> getAll();
}
