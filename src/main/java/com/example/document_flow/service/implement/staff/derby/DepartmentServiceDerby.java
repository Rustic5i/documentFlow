package com.example.document_flow.service.implement.staff.derby;

import com.example.document_flow.DAO.abstraction.DepartmentDAO;
import com.example.document_flow.DAO.implement.dao.staff.DepartmentDAOImpl;
import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
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

    private static DepartmentServiceDerby departmentServiceDerby;

    private final DepartmentDAO dao = DepartmentDAOImpl.getInstance();

    private DepartmentServiceDerby() {
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

    /**
     * Сохранить объект класса <code>Department</code>
     *
     * @param object объект, который требуется сохранить
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void save(Department object) throws SaveObjectException {
        dao.save(object);
    }

    /**
     * Сохранить список объектов класса <code>Department</code>
     *
     * @param objects список объектов класса <code>Department</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void saveAll(List<Department> objects) throws SaveObjectException {
        dao.saveAll(objects);
    }

    /**
     * Получить список всех сохраненных объектов класса <code>Department</code>
     *
     * @return список сохраненных объектов класса <code>Department</code>
     * @throws GetDataObjectException когда получение данных терпит неудачу по какой-либо причине.
     */
    @Override
    public List<Department> getAll() throws GetDataObjectException {
        return dao.getAll();
    }

    /**
     * Найти объект класса <code>Department</code> по id
     *
     * @param id id объекта класса <code>Department</code>
     * @return найденный объект класса <code>Department</code>
     * @throws GetDataObjectException когда получение данных терпит неудачу по какой-либо причине.
     */
    @Override
    public Optional<Department> findById(long id) throws GetDataObjectException {
        return dao.findById(id);
    }

    /**
     * Удалить объект по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        dao.deleteById(id);
    }

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(Department object) throws SaveObjectException {
        dao.update(object);
    }

    @Override
    public List<Department> findByIdOrganization(long id) throws GetDataObjectException {
        return dao.findByIdOrganization(id);
    }
}
