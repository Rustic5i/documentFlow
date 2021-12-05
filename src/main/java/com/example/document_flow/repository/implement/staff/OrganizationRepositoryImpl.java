package com.example.document_flow.repository.implement.staff;

import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.repository.absraction.staff.OrganizationRepository;

import java.util.List;

/**
 * Репозиторий для сущности Organization
 *
 * @author Баратов Руслан
 */
public class OrganizationRepositoryImpl implements OrganizationRepository {

    private final StaffRepositoryXml<Organization> REPOSITORY = new StaffRepositoryXml<>(Organization.class);

    private static OrganizationRepositoryImpl organizationRepository;

    private OrganizationRepositoryImpl() {
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
    public Organization findById(long id) {
        return REPOSITORY.findById(id);
    }

    /**
     * @return синголтон объект
     */
    public static OrganizationRepositoryImpl getInstance() {
        if (organizationRepository == null) {
            organizationRepository = new OrganizationRepositoryImpl();
        }
        return organizationRepository;
    }
}
