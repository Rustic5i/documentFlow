package com.example.document_flow.service.abstraction;

import java.util.List;

/**
 * Сервис
 * список общих методов для взаимодействия с сервисом
 *
 * @param <T> тип сервиса
 * @author Баратов Руслан
 */
public interface Service<T> {

    /**
     * Сохранить обьект
     *
     * @param object какой-либо обьект
     */
    void save(T object);

    /**
     * Сохранить список обьектов
     *
     * @param objects список каких-либо обьектов
     */
    void saveAll(List<T> objects);

    /**
     * Получить все обьекты
     *
     * @return список каких-либо обьектов
     */
    List<T> getAll();
}
