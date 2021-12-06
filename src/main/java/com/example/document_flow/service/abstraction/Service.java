package com.example.document_flow.service.abstraction;

import com.example.document_flow.exception.SaveObjectException;

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
     * Сохранить объект
     *
     * @param object объект, который требуется сохранить
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    void save(T object) throws SaveObjectException;

    /**
     * Сохранить список обьектов
     *
     * @param objects список каких-либо объектов
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    void saveAll(List<T> objects) throws SaveObjectException;

    /**
     * Получить все объекты
     *
     * @return список каких-либо объектов
     */
    List<T> getAll();
}
