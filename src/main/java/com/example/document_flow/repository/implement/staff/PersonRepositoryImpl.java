package com.example.document_flow.repository.implement.staff;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.repository.absraction.staff.PersonRepository;

import java.util.List;

/**
 * Репозиторий для сущности Person
 *
 * @author Баратов Руслан
 */
public class PersonRepositoryImpl implements PersonRepository {

    private StaffRepositoryXml<Person> REPOSITORY = new StaffRepositoryXml<>(Person.class);

    private static PersonRepositoryImpl personRepository;

    private PersonRepositoryImpl() {
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
     * @return синголтон обьект
     */
    public static PersonRepositoryImpl getInstance() {
        if (personRepository == null) {
            personRepository = new PersonRepositoryImpl();
        }
        return personRepository;
    }
}
