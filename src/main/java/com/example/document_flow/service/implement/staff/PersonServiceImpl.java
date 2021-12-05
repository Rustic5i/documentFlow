package com.example.document_flow.service.implement.staff;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.repository.absraction.staff.PersonRepository;
import com.example.document_flow.repository.implement.staff.PersonRepositoryImpl;
import com.example.document_flow.service.abstraction.staff.PersonService;

import java.util.List;

/**
 * Класс сервис для управления Person
 *
 * @author Баратов Руслан
 */
public class PersonServiceImpl implements PersonService {

    private final PersonRepository REPOSITORY = PersonRepositoryImpl.getInstance();

    private static PersonServiceImpl personService = new PersonServiceImpl();

    private PersonServiceImpl() {
    }

    /**
     * Сохранить работника
     *
     * @param object работник
     */
    @Override
    public void save(Person object) {
        REPOSITORY.save(object);
    }

    /**
     * Сохранить список работников
     *
     * @param objects лист работников
     */
    @Override
    public void saveAll(List<Person> objects) {
        REPOSITORY.saveAll(objects);
    }

    /**
     * Получить всех работников
     *
     * @return список работников
     */
    @Override
    public List<Person> getAll() {
        return REPOSITORY.getAll();
    }

    /**
     * Найти объект класса <code>Person</code> по id
     *
     * @param id id объекта класса <code>Person</code>
     * @return найденный объект класса <code>Person</code>
     */
    @Override
    public Person findPersonById(long id) {
        return REPOSITORY.findById(id);
    }

    /**
     * @return синголтон обьект
     */
    public static PersonServiceImpl getInstance() {
        if (personService == null) {
            personService = new PersonServiceImpl();
        }
        return personService;
    }
}
