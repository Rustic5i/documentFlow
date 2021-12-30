package com.example.document_flow.DAO.abstraction;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.exception.GetDataObjectException;

import java.util.List;

public interface DepartmentDAO extends DAOCrud<Department> {

    List<Department> findByIdOrganization(long id) throws GetDataObjectException;
}
