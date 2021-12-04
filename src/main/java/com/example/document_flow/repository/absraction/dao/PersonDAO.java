package com.example.document_flow.repository.absraction.dao;

import com.example.document_flow.entity.staff.Person;

import java.util.List;

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
     */
    void savePerson(Person person);

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
     */
    void saveAllPerson(List<Person> personList);

    /**
     * Найти объект класса <code>Person</code> по id
     *
     * @param id id объекта класса <code>Person</code>
     * @return найденный объект класса <code>Person</code>
     */
    Person findPersonById(long id);
}
