package com.example.document_flow.service.abstraction.staff;

import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.service.abstraction.Service;

import java.util.Optional;

/**
 * Интерфейс сервис для управления Person
 *
 * @author Баратов Руслан
 */
public interface PersonService extends Service<Person> {

    /**
     * Найти объект класса <code>Person</code> по id
     *
     * @param id id объекта класса <code>Person</code>
     * @return найденный объект класса <code>Person</code>
     */
    Optional<Person> findPersonById(long id);

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
    void update(Person object);
}
