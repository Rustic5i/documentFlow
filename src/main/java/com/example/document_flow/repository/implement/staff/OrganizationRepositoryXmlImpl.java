package com.example.document_flow.repository.implement.staff;

import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.repository.absraction.staff.OrganizationRepository;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для сущности Organization
 *
 * @author Баратов Руслан
 */
public class OrganizationRepositoryXmlImpl implements OrganizationRepository {

    private final StaffRepositoryXml<Organization> REPOSITORY = new StaffRepositoryXml<>(Organization.class);

    private static OrganizationRepositoryXmlImpl organizationRepository;

    {
        REPOSITORY.createRepository(new File(".\\OrganizationXml\\"));
    }

    private OrganizationRepositoryXmlImpl() {
    }

    /**
     * Сохраняет объект Organization в репозиторий
     *
     * @param object объект для сохранения
     */
    @Override
    public void save(Organization object) {
        REPOSITORY.save(object);
    }

    /**
     * Сохраняет список объектов Organization в репозиторий
     *
     * @param objects список объектов для сохранения
     */
    @Override
    public void saveAll(List<Organization> objects) {
        REPOSITORY.saveAll(objects);
    }

    /**
     * Получаем все сохраненные объекты из репозитория
     *
     * @return список объектов Organization
     */
    @Override
    public List<Organization> getAll() {
        return REPOSITORY.getAll();
    }

    /**
     * Искать сохраненный объект по id
     *
     * @param id id объекта
     * @return найденный объект
     */
    @Override
    public Optional<Organization> findById(long id) {
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
    public void update(Organization object) throws SaveObjectException {
        REPOSITORY.update(object);
    }

    /**
     * @return синголтон объект
     */
    public static OrganizationRepositoryXmlImpl getInstance() {
        if (organizationRepository == null) {
            organizationRepository = new OrganizationRepositoryXmlImpl();
        }
        return organizationRepository;
    }
}
