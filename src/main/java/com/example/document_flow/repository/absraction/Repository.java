package com.example.document_flow.repository.absraction;

import java.util.List;
import java.util.Optional;

/**
 * Список общих методов для взаимодействия с базой данных/репозиторий
 *
 * @param <T> тип объектов
 * @author Баратов Руслан
 */
public interface Repository<T> {

    /**
     * Сохранить какой-либо обьект в репозиторий
     *
     * @param object обьект для сохранения
     */
    void save(T object);

    /**
     * Сохранить список обьектов в репозиторий
     *
     * @param objects список обьектов для сохранения
     */
    void saveAll(List<T> objects);

    /**
     * Получить все сохраненные обьекты из репозитория
     *
     * @return список сохраненных обьектов
     */
    List<T> getAll();

    /**
     * Найти объект по id
     *
     * @param id id объекта
     * @return найденный объект
     */
    Optional<T> findById(long id);
}
