package com.example.document_flow.service.implement.staff.xml;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.SaveObjectException;
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

    private static DepartmentServiceXmlImpl departmentService;

    private final DepartmentRepository REPOSITORY = DepartmentRepositoryXmlImpl.getInstance();

    private DepartmentServiceXmlImpl() {
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

    /**
     * Сохранить подразделение
     *
     * @param object подразделение
     */
    @Override
    public void save(Department object) throws SaveObjectException {
        REPOSITORY.save(object);
    }

    /**
     * Сохранить список подразделений
     *
     * @param objects список подразделений
     */
    @Override
    public void saveAll(List<Department> objects) throws SaveObjectException {
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
    public Optional<Department> findById(long id) {
        return REPOSITORY.findById(id);
    }

    /**
     * Удалить объект по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        REPOSITORY.deleteById(id);
    }

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(Department object) throws SaveObjectException {
        REPOSITORY.update(object);
    }
}
