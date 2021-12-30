package com.example.document_flow.DAO.abstraction;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.GetDataObjectException;

import java.util.List;

public interface PersonDAO extends DAOCrud<Person> {

    List<Person> findByIdDepartment(long id) throws GetDataObjectException;
}
