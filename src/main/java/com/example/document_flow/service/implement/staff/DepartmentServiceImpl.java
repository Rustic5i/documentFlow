package com.example.document_flow.service.implement.staff;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.repository.absraction.staff.DepartmentRepository;
import com.example.document_flow.repository.implement.staff.DepartmentRepositoryImpl;
import com.example.document_flow.repository.implement.staff.StaffRepositoryXml;
import com.example.document_flow.service.abstraction.Service;
import com.example.document_flow.service.abstraction.staff.DepartmentService;

import java.util.List;

/**
 * Класс сервис для управления Department
 *
 * @author Баратов Руслан
 */
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository REPOSITORY = DepartmentRepositoryImpl.getInstance();

    private static DepartmentServiceImpl departmentService;

    private DepartmentServiceImpl() {
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
    public static DepartmentServiceImpl getInstance() {
        if (departmentService == null) {
            departmentService = new DepartmentServiceImpl();
        }
        return departmentService;
    }
}
