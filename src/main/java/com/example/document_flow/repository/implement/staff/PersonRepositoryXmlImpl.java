package com.example.document_flow.repository.implement.staff;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.repository.absraction.staff.PersonRepository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для сущности Person
 *
 * @author Баратов Руслан
 */
public class PersonRepositoryXmlImpl implements PersonRepository {

    private StaffRepositoryXml<Person> REPOSITORY = new StaffRepositoryXml<>(Person.class);

    private static PersonRepositoryXmlImpl personRepository;

    private PersonRepositoryXmlImpl() {
    }

    /**
     * Сохраняет объект Person в репозиторий
     *
     * @param object объект для сохранения
     */
    @Override
    public void save(Person object) {
        REPOSITORY.save(object);
    }

    /**
     * Сохраняет список объектов Person в репозиторий
     *
     * @param objects список объектов для сохранения
     */
    @Override
    public void saveAll(List<Person> objects) {
        REPOSITORY.saveAll(objects);
    }

    /**
     * Получаем все сохраненные объекты из репозитория
     *
     * @return список объектов Person
     */
    @Override
    public List<Person> getAll() {
        return REPOSITORY.getAll();
    }

    /**
     * Искать сохраненный объект по id
     *
     * @param id id объекта
     * @return найденный объект
     */
    @Override
    public Optional<Person> findById(long id) {
        return REPOSITORY.findById(id);
    }

    /**
     * @return синголтон обьект
     */
    public static PersonRepositoryXmlImpl getInstance() {
        if (personRepository == null) {
            personRepository = new PersonRepositoryXmlImpl();
        }
        return personRepository;
    }
}
