package com.example.document_flow.service.implement.staff.xml;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.repository.absraction.staff.PersonRepository;
import com.example.document_flow.repository.implement.staff.PersonRepositoryXmlImpl;
import com.example.document_flow.service.abstraction.staff.PersonService;

import java.util.List;
import java.util.Optional;

/**
 * Класс сервис для управления Person
 *
 * @author Баратов Руслан
 */
public class PersonServiceXmlImpl implements PersonService {

    private final PersonRepository REPOSITORY = PersonRepositoryXmlImpl.getInstance();

    private static PersonServiceXmlImpl personService = new PersonServiceXmlImpl();

    private PersonServiceXmlImpl() {
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
    public Optional<Person> findById(long id) {
        return REPOSITORY.findById(id);
    }

    /**
     * Удалить объект по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        REPOSITORY.deleteById(id);
    }

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(Person object) throws SaveObjectException {
        REPOSITORY.update(object);
    }

    /**
     * @return синголтон обьект
     */
    public static PersonServiceXmlImpl getInstance() {
        if (personService == null) {
            personService = new PersonServiceXmlImpl();
        }
        return personService;
    }
}
