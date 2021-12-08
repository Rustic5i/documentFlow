package com.example.document_flow.repository.implement.staff;

import com.example.document_flow.entity.staff.Department;
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

    private final StaffRepositoryXml<Department> REPOSITORY = new StaffRepositoryXml<>(Department.class);

    private static DepartmentRepositoryXmlImpl departmentRepository;

    {
        REPOSITORY.createRepository(new File(".\\DepartmentXml\\"));
    }

    private DepartmentRepositoryXmlImpl() {
    }

    /**
     * Сохраняет объект Department в репозиторий
     *
     * @param object объект для сохранения
     */
    @Override
    public void save(Department object) {
        REPOSITORY.save(object);
    }

    /**
     * Сохраняет список объектов Department в репозиторий
     *
     * @param objects список объектов для сохранения
     */
    @Override
    public void saveAll(List<Department> objects) {
        REPOSITORY.saveAll(objects);
    }

    /**
     * Получаем все сохраненные объекты из репозитория
     *
     * @return список объектов Department
     */
    @Override
    public List<Department> getAll() {
        return REPOSITORY.getAll();
    }

    /**
     * Искать сохраненный объект по id
     *
     * @param id id объекта
     * @return найденный объект
     */
    @Override
    public Optional<Department> findById(long id) {
        return REPOSITORY.findById(id);
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
}
