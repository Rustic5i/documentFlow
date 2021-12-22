package com.example.document_flow.repository.implement.staff;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.repository.absraction.staff.DepartmentRepository;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для сущности Department
 *
 * @author Баратов Руслан
 */
public class DepartmentRepositoryXmlImpl implements DepartmentRepository {

    private static DepartmentRepositoryXmlImpl departmentRepository;

    private final StaffRepositoryXml<Department> repositoryXml = new StaffRepositoryXml<>(Department.class);

    {
        repositoryXml.createRepository(new File(".\\DepartmentXml\\"));
    }

    /**
     * @return синголтон обьект
     */
    public static DepartmentRepositoryXmlImpl getInstance() {
        if (departmentRepository == null) {
            departmentRepository = new DepartmentRepositoryXmlImpl();
        }
        return departmentRepository;
    }

    private DepartmentRepositoryXmlImpl() {
    }

    /**
     * Сохраняет объект Department в репозиторий
     *
     * @param object объект для сохранения
     * @throws SaveObjectException когда сохранения объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void save(Department object) throws SaveObjectException {
        repositoryXml.save(object);
    }

    /**
     * Сохраняет список объектов Department в репозиторий
     *
     * @param objects список объектов для сохранения
     * @throws SaveObjectException когда сохранения объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void saveAll(List<Department> objects) throws SaveObjectException {
        repositoryXml.saveAll(objects);
    }

    /**
     * Получаем все сохраненные объекты из репозитория
     *
     * @return список объектов Department
     */
    @Override
    public List<Department> getAll() {
        return repositoryXml.getAll();
    }

    /**
     * Искать сохраненный объект по id
     *
     * @param id id объекта
     * @return найденный объект
     */
    @Override
    public Optional<Department> findById(long id) {
        return repositoryXml.findById(id);
    }

    /**
     * Удалить объект по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        repositoryXml.deleteById(id);
    }

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(Department object) throws SaveObjectException {
        repositoryXml.update(object);
    }
}
