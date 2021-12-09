package com.example.document_flow.service.implement.staff.derby;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.DAO.implement.derby.DepartmentDerbyDataBase;
import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.service.abstraction.staff.DepartmentService;

import java.util.List;
import java.util.Optional;

/**
 * Сервисный слой для управления <code>Department</code> в Derby Data base
 *
 * @author Баратов Руслан
 */
public class DepartmentServiceDerby implements DepartmentService {

    private final DAOCrud<Department> DAO = DepartmentDerbyDataBase.getInstance();

    private static DepartmentServiceDerby departmentServiceDerby;

    private DepartmentServiceDerby() {
    }

    /**
     * Сохранить объект класса <code>Department</code>
     *
     * @param object объект, который требуется сохранить
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void save(Department object) throws SaveObjectException {
        DAO.save(object);
    }

    /**
     * Сохранить список объектов класса <code>Department</code>
     *
     * @param objects список объектов класса <code>Department</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void saveAll(List<Department> objects) throws SaveObjectException {
        DAO.saveAll(objects);
    }

    /**
     * Получить список всех сохраненных объектов класса <code>Department</code>
     *
     * @return список сохраненных объектов класса <code>Department</code>
     */
    @Override
    public List<Department> getAll() {
        return DAO.getAll();
    }

    /**
     * Найти объект класса <code>Department</code> по id
     *
     * @param id id объекта класса <code>Department</code>
     * @return найденный объект класса <code>Department</code>
     */
    @Override
    public Optional<Department> findDepartmentById(long id) {
        return DAO.findById(id);
    }

    /**
     * Удалить объект по id
     *
     * @param id - id объекта
     */
    @Override
    public void deleteById(long id) {
        DAO.deleteById(id);
    }

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     */
    @Override
    public void update(Department object) {
        DAO.update(object);
    }

    /**
     * @return синголтон обьект
     */
    public static DepartmentServiceDerby getInstance() {
        if (departmentServiceDerby == null) {
            departmentServiceDerby = new DepartmentServiceDerby();
        }
        return departmentServiceDerby;
    }
}
