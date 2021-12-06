package com.example.document_flow.repository.absraction.dao;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.SaveObjectException;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс dao, определяющий методы доступа к данным для экземпляров класса <code>Person</code>
 *
 * @author Баратов Руслан
 */
public interface PersonDAO {

    /**
     * Сохранить объект класса <code>Person</code>
     *
     * @param person объект класса <code>Person</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    void savePerson(Person person) throws SaveObjectException;

    /**
     * Получить список всех сохраненных объектов класса <code>Person</code>
     *
     * @return список сохраненных объектов класса <code>Person</code>
     */
    List<Person> getAllPerson();

    /**
     * Сохранить список объектов класса <code>Person</code>
     *
     * @param personList список объектов класса <code>Person</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    void saveAllPerson(List<Person> personList) throws SaveObjectException;

    /**
     * Найти объект класса <code>Person</code> по id
     *
     * @param id id объекта класса <code>Person</code>
     * @return найденный объект класса <code>Person</code>
     */
    Optional<Person> findPersonById(long id);
}
