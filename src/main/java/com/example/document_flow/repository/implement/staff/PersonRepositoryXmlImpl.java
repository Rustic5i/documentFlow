package com.example.document_flow.repository.implement.staff;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.repository.absraction.staff.PersonRepository;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для сущности Person
 *
 * @author Баратов Руслан
 */
public class PersonRepositoryXmlImpl implements PersonRepository {

    private static PersonRepositoryXmlImpl personRepository;

    private final StaffRepositoryXml<Person> repositoryXml = new StaffRepositoryXml<>(Person.class);

    {
        repositoryXml.createRepository(new File(".\\PersonXml\\"));
    }

    private PersonRepositoryXmlImpl() {
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

    /**
     * Сохраняет объект Person в репозиторий
     *
     * @param object объект для сохранения
     * @throws SaveObjectException когда сохранения объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void save(Person object) throws SaveObjectException {
        repositoryXml.save(object);
    }

    /**
     * Сохраняет список объектов Person в репозиторий
     *
     * @param objects список объектов для сохранения
     * @throws SaveObjectException когда сохранения объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void saveAll(List<Person> objects) throws SaveObjectException {
        repositoryXml.saveAll(objects);
    }

    /**
     * Получаем все сохраненные объекты из репозитория
     *
     * @return список объектов Person
     */
    @Override
    public List<Person> getAll() {
        return repositoryXml.getAll();
    }

    /**
     * Искать сохраненный объект по id
     *
     * @param id id объекта
     * @return найденный объект
     */
    @Override
    public Optional<Person> findById(long id) {
        return repositoryXml.findById(id);
    }


    /**
     * Удалить объект по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        repositoryXml.deleteById(id);
    }

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(Person object) throws SaveObjectException {
        repositoryXml.update(object);
    }
}
