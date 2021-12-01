package com.example.document_flow.service.implement;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.repository.DAO.DAO;
import com.example.document_flow.repository.staff.StaffRepositoryXml;
import com.example.document_flow.service.abstraction.AbstractService;

import java.util.List;

/**
 * Сервис по работникам
 * @author Баратов Руслан
 */
public class PersonService implements AbstractService<Person> {

    private final DAO<Person> REPOSITORY = new StaffRepositoryXml<>(Person.class);

    private static PersonService personService = new PersonService();

    private PersonService() {
    }

    /**
     * Сохранить работника
     * @param object работник
     */
    @Override
    public void save(Person object) {
        REPOSITORY.save(object);
    }

    /**
     * Сохранить список работников
     * @param objects лист работников
     */
    @Override
    public void saveAll(List<Person> objects) {
        REPOSITORY.saveAll(objects);
    }

    /**
     * Получить всех работников
     * @return список работников
     */
    @Override
    public List<Person> getAll() {
        return REPOSITORY.getAll();
    }

    /**
     * @return синголтон обьект
     */
    public static PersonService getInstance() {
        if (personService == null) {
            personService = new PersonService();
        }
        return personService;
    }
}
