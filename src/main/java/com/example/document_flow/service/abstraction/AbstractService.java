package com.example.document_flow.service.abstraction;

import java.util.List;

/**
 * Сервис
 * список общих методов для взаимодействия с сервисом
 *
 * @param <T> тип сервиса
 * @author Баратов Руслан
 */
public interface AbstractService<T> {

    /**
     * Сохранить объект
     *
     * @param object какой-либо объект
     */
    void save(T object);

    /**
     * Сохранить список обьектов
     *
     * @param objects список каких-либо объектов
     */
    void saveAll(List<T> objects);

    /**
     * Получить все объекты
     *
     * @return список каких-либо объектов
     */
    List<T> getAll();
}
