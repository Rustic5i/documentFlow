package com.example.document_flow.service.implement;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.repository.DAO.DAO;
import com.example.document_flow.repository.staff.StaffRepositoryXml;
import com.example.document_flow.service.abstraction.AbstractService;

import java.util.List;

/**
 * Сервис для Подразделений
 *
 * @author Баратов Руслан
 */
public class DepartmentService implements AbstractService<Department> {

    private final DAO<Department> REPOSITORY = new StaffRepositoryXml<>(Department.class);

    private static DepartmentService departmentService;

    private DepartmentService() {
    }

    /**
     * Сохранить подразделение
     *
     * @param object подразделение
     */
    @Override
    public void save(Department object) {
        REPOSITORY.save(object);
    }

    /**
     * Сохранить список подразделений
     *
     * @param objects список подразделений
     */
    @Override
    public void saveAll(List<Department> objects) {
        REPOSITORY.saveAll(objects);
    }

    /**
     * Получить все подразделения
     *
     * @return список подразделений
     */
    @Override
    public List<Department> getAll() {
        return REPOSITORY.getAll();
    }

    /**
     * @return синголтон обьект
     */
    public static DepartmentService getInstance() {
        if (departmentService == null) {
            departmentService = new DepartmentService();
        }
        return departmentService;
    }
}
