package com.example.document_flow.service.implement.staff.derby;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.DAO.implement.derby.PersonDerbyDataBase;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.service.abstraction.staff.PersonService;

import java.util.List;
import java.util.Optional;

/**
 * Сервисный слой для управления <code>Person</code> в Derby Data base
 *
 * @author Баратов Руслан
 */
public class PersonServiceDerby implements PersonService {

    private final DAOCrud<Person> DAO = PersonDerbyDataBase.getInstance();

    private static PersonServiceDerby personServiceDerby;

    private PersonServiceDerby() {
    }

    /**
     * Сохранить объект класса <code>Person</code>
     *
     * @param object объект, который требуется сохранить
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void save(Person object) throws SaveObjectException {
        DAO.save(object);
    }

    /**
     * Сохранить список объектов класса <code>Person</code>
     *
     * @param objects список объектов класса <code>Person</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void saveAll(List<Person> objects) throws SaveObjectException {
        DAO.saveAll(objects);
    }

    /**
     * Получить список всех сохраненных объектов класса <code>Person</code>
     *
     * @return список сохраненных объектов класса <code>Person</code>
     */
    @Override
    public List<Person> getAll() {
        return DAO.getAll();
    }

    /**
     * Найти объект класса <code>Person</code> по id
     *
     * @param id id объекта класса <code>Person</code>
     * @return найденный объект класса <code>Person</code>
     */
    @Override
    public Optional<Person> findById(long id) {
        return DAO.findById(id);
    }

    /**
     * Удалить объект по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        DAO.deleteById(id);
    }

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(Person object) throws SaveObjectException {
        DAO.update(object);
    }

    /**
     * @return синголтон обьект
     */
    public static PersonServiceDerby getInstance() {
        if (personServiceDerby == null) {
            personServiceDerby = new PersonServiceDerby();
        }
        return personServiceDerby;
    }
}
