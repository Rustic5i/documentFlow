package com.example.document_flow.repository.absraction.dao;

import com.example.document_flow.entity.staff.Department;

import java.util.List;

/**
 * Интерфейс dao, определяющий методы доступа к данным для экземпляров класса <code>Department</code>
 *
 * @author Баратов Руслан
 */
public interface DepartmentDAO {

    /**
     * Сохранить объект класса  <code>Department</code>
     *
     * @param department объект класса <code>Department</code> для сохранения
     */
    void saveDepartment(Department department);

    /**
     * Получить список всех сохраненных объектов класса <code>Department</code>
     *
     * @return список сохраненных объектов класса <code>Department</code>
     */
    List<Department> getAllDepartment();

    /**
     * Сохранить список объектов класса <code>Department</code>
     *
     * @param departmentList список объектов класса <code>Department</code> для сохранения
     */
    void saveAllDepartment(List<Department> departmentList);

    /**
     * Найти объект класса <code>Department</code> по id
     *
     * @param id id объекта класса <code>Department</code>
     * @return найденный объект класса <code>Department</code>
     */
    Department findDepartmentById(long id);
}
