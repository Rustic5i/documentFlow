package com.example.document_flow.repository.absraction;

import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.SaveObjectException;

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
     * Сохранить какой-либо объект в репозиторий
     *
     * @param object объект для сохранения
     * @throws SaveObjectException когда сохранения объекта терпит не удачу по какой-либо причине
     */
    void save(T object) throws SaveObjectException;

    /**
     * Сохранить список объектов в репозиторий
     *
     * @param objects список объектов для сохранения
     * @throws SaveObjectException когда сохранения объекта терпит не удачу по какой-либо причине
     */
    void saveAll(List<T> objects) throws SaveObjectException;

    /**
     * Получить все сохраненные обьекты из репозитория
     *
     * @return список сохраненных объектов
     */
    List<T> getAll();

    /**
     * Найти объект по id
     *
     * @param id id объекта
     * @return найденный объект
     */
    Optional<T> findById(long id);

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

}
