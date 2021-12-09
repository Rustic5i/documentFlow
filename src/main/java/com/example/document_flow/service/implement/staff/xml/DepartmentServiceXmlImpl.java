package com.example.document_flow.service.implement.staff.xml;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.repository.absraction.staff.DepartmentRepository;
import com.example.document_flow.repository.implement.staff.DepartmentRepositoryXmlImpl;
import com.example.document_flow.service.abstraction.staff.DepartmentService;

import java.util.List;
import java.util.Optional;

/**
 * Класс сервис для управления Department
 *
 * @author Баратов Руслан
 */
public class DepartmentServiceXmlImpl implements DepartmentService {

    private final DepartmentRepository REPOSITORY = DepartmentRepositoryXmlImpl.getInstance();

    private static DepartmentServiceXmlImpl departmentService;

    private DepartmentServiceXmlImpl() {
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
     * Найти объект класса <code>Department</code> по id
     *
     * @param id id объекта класса <code>Department</code>
     * @return найденный объект класса <code>Department</code>
     */
    @Override
    public Optional<Department> findDepartmentById(long id) {
        return REPOSITORY.findById(id);
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void update(Department object) {

    }

    /**
     * @return синголтон обьект
     */
    public static DepartmentServiceXmlImpl getInstance() {
        if (departmentService == null) {
            departmentService = new DepartmentServiceXmlImpl();
        }
        return departmentService;
    }
}
