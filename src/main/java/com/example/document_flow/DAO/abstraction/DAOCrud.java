package com.example.document_flow.DAO.abstraction;

import com.example.document_flow.exception.SaveObjectException;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс dao, с основными CRUD методами для доступа к данным
 *
 * @author Баратов Руслан
 */
public interface DAOCrud<T> {

    /**
     * Сохранить объект
     *
     * @param object объект для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    void save(T object) throws SaveObjectException;

    /**
     * Удалить объект по id
     *
     * @param id - id объекта
     */
    void deleteById(long id);

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     */
    void update(T object);

    /**
     * Получить список всех сохраненных объектов
     *
     * @return список сохраненных объектов
     */
    List<T> getAll();

    /**
     * Сохранить список объектов
     *
     * @param objectList список объектов для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    void saveAll(List<T> objectList) throws SaveObjectException;

    /**
     * Найти объект класса <code>Department</code> по id
     *
     * @param id id объекта класса <code>Department</code>
     * @return найденный объект класса <code>Department</code>
     */
    Optional<T> findById(long id);
}
