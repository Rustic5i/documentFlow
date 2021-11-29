package com.example.document_flow.service.implement;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.repository.DAO.DAO;
import com.example.document_flow.repository.staff.StaffRepositoryXml;
import com.example.document_flow.service.abstraction.Service;

import java.util.List;

/**
 * Серсив для Подразделений
 *
 * @author Баратов Руслан
 */
public class DepartmentServiceXml implements Service<Department> {

    private DAO<Department> repository = new StaffRepositoryXml<>(Department.class);

    private static DepartmentServiceXml departmentService;

    private DepartmentServiceXml() {
    }

    /**
     * Сохранить подразделение
     *
     * @param object подразделение
     */
    @Override
    public void save(Department object) {
        repository.save(object);
    }

    /**
     * Сохранить список подразделений
     *
     * @param objects список подразделений
     */
    @Override
    public void saveAll(List<Department> objects) {
        repository.saveAll(objects);
    }

    /**
     * Получить все подразделения
     *
     * @return список подразделений
     */
    @Override
    public List<Department> getAll() {
        return repository.getAll();
    }

    /**
     * @return синголтон обьект
     */
    public static DepartmentServiceXml getInstance() {
        if (departmentService == null) {
            departmentService = new DepartmentServiceXml();
        }
        return departmentService;
    }
}
