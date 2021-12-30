package com.example.document_flow.service.abstraction.staff;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.service.abstraction.Service;

import java.util.List;

/**
 * Интерфейс сервис для управления Department
 *
 * @author Баратов Руслан
 */
public interface DepartmentService extends Service<Department> {

    List<Department> findByIdOrganization(long id) throws GetDataObjectException;
}
