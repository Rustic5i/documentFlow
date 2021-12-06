package com.example.document_flow.repository.absraction.dao;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.exception.SaveObjectException;

import java.util.List;
import java.util.Optional;

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
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    void saveDepartment(Department department) throws SaveObjectException;

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
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    void saveAllDepartment(List<Department> departmentList) throws SaveObjectException;

    /**
     * Найти объект класса <code>Department</code> по id
     *
     * @param id id объекта класса <code>Department</code>
     * @return найденный объект класса <code>Department</code>
     */
    Optional<Department> findDepartmentById(long id);
}
