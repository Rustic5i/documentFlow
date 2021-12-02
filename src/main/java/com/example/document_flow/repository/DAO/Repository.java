package com.example.document_flow.repository.DAO;

import java.util.List;

/**
 * Cписок общих методов для взаимодействия с базой данных/репозиторий
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

}
