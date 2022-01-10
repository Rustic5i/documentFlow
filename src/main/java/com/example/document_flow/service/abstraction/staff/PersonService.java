package com.example.document_flow.service.abstraction.staff;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.service.abstraction.Service;

import java.util.List;

/**
 * Интерфейс сервис для управления Person
 *
 * @author Баратов Руслан
 */
public interface PersonService extends Service<Person> {

    List<Person> findByIdDepartment(long id) throws GetDataObjectException;
}
