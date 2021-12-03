package com.example.document_flow.repository.absraction.dao;

import com.example.document_flow.entity.staff.Person;

import java.util.List;

public interface PersonDAO {

    void savePerson(Person person);

    List<Person> getAllPerson();

    void saveAllPerson(List<Person> personList);

    Person getPersonById(long id);
}
