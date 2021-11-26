package com.example.document_flow.service.implement;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.repository.abstraction.DAO.DAO;
import com.example.document_flow.repository.staff.implement.RepositoryXml;
import com.example.document_flow.repository.staff.implement.RepositoryXmlTest;
import com.example.document_flow.service.abstraction.Service;

import java.util.List;

/**
 * Сервис по работникам
 * @author Баратов Руслан
 */
public class PersonService implements Service<Person> {

    private final DAO<Person> repository = new RepositoryXmlTest<>(Person.class);

    private static PersonService personService = new PersonService();

    private PersonService() {
    }

    /**
     * Сохранить работника
     * @param object работник
     */
    @Override
    public void save(Person object) {
        repository.save(object);
    }

    /**
     * Сохранить список работников
     * @param objects лист работников
     */
    @Override
    public void saveAll(List<Person> objects) {
        repository.saveAll(objects);
    }

    /**
     * Получить всех работников
     * @return список работников
     */
    @Override
    public List<Person> getAll() {
        return repository.getAll();
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
