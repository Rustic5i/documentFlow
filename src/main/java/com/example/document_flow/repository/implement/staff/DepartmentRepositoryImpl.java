package com.example.document_flow.repository.implement.staff;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.repository.absraction.staff.DepartmentRepository;

import java.util.List;

/**
 * Репозиторий для сущности Department
 *
 * @author Баратов Руслан
 */
public class DepartmentRepositoryImpl implements DepartmentRepository {

    private final StaffRepositoryXml<Department> REPOSITORY = new StaffRepositoryXml<>(Department.class);

    private static DepartmentRepositoryImpl departmentRepository;

    private DepartmentRepositoryImpl() {
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
     * @return синголтон обьект
     */
    public static DepartmentRepositoryImpl getInstance() {
        if (departmentRepository == null) {
            departmentRepository = new DepartmentRepositoryImpl();
        }
        return departmentRepository;
    }
}
