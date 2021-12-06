package com.example.document_flow.service.abstraction.staff;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.service.abstraction.Service;

import java.util.Optional;

/**
 * Интерфейс сервис для управления Department
 *
 * @author Баратов Руслан
 */
public interface DepartmentService extends Service<Department> {

    /**
     * Найти объект класса <code>Department</code> по id
     *
     * @param id id объекта класса <code>Department</code>
     * @return найденный объект класса <code>Department</code>
     */
    Optional<Department> findDepartmentById(long id);
}
