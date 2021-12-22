package com.example.document_flow.service.abstraction;

import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;

import java.util.List;
import java.util.Optional;

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
     * @throws GetDataObjectException когда получение данных терпит неудачу по какой-либо причине.
     */
    List<T> getAll() throws GetDataObjectException;

    /**
     * Удалить объект по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    void deleteById(long id) throws DeleteObjectException;

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    void update(T object) throws SaveObjectException;

    /**
     * Найти объект по id
     *
     * @param id id объекта класса <code>Department</code>
     * @return найденный объект класса <code>Department</code>
     * @throws GetDataObjectException когда получение данных терпит неудачу по какой-либо причине.
     */
    Optional<T> findById(long id) throws GetDataObjectException;
}
