package com.example.document_flow.service.implement.staff.derby;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.repository.absraction.dao.PersonDAO;
import com.example.document_flow.repository.implement.derby.PersonDerbyDataBase;
import com.example.document_flow.service.abstraction.staff.PersonService;

import java.util.List;
import java.util.Optional;

/**
 * Сервисный слой для управления <code>Person</code> в Derby Data base
 *
 * @author Баратов Руслан
 */
public class PersonServiceDerby implements PersonService {

    private final PersonDAO DAO = PersonDerbyDataBase.getInstance();

    /**
     * Сохранить объект класса <code>Person</code>
     *
     * @param object объект, который требуется сохранить
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void save(Person object) throws SaveObjectException {
        DAO.savePerson(object);
    }

    /**
     * Сохранить список объектов класса <code>Person</code>
     *
     * @param objects список объектов класса <code>Person</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void saveAll(List<Person> objects) throws SaveObjectException {
        DAO.saveAllPerson(objects);
    }

    /**
     * Получить список всех сохраненных объектов класса <code>Person</code>
     *
     * @return список сохраненных объектов класса <code>Person</code>
     */
    @Override
    public List<Person> getAll() {
        return DAO.getAllPerson();
    }

    /**
     * Найти объект класса <code>Person</code> по id
     *
     * @param id id объекта класса <code>Person</code>
     * @return найденный объект класса <code>Person</code>
     */
    @Override
    public Optional<Person> findPersonById(long id) {
        return DAO.findPersonById(id);
    }
}
