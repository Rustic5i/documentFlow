package com.example.document_flow.service.implement;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.repository.DAO.Repository;
import com.example.document_flow.repository.staff.StaffRepositoryXml;
import com.example.document_flow.service.abstraction.Service;

import java.util.List;

/**
 * Сервис по работникам
 * @author Баратов Руслан
 */
public class PersonServiceXml implements Service<Person> {

    private final Repository<Person> REPOSITORY = new StaffRepositoryXml<>(Person.class);

    private static PersonServiceXml personService = new PersonServiceXml();

    private PersonServiceXml() {
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
    public static PersonServiceXml getInstance() {
        if (personService == null) {
            personService = new PersonServiceXml();
        }
        return personService;
    }
}
