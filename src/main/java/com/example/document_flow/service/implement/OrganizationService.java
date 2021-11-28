package com.example.document_flow.service.implement;

import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.repository.DAO.DAO;
import com.example.document_flow.repository.staff.implement.RepositoryXml;
import com.example.document_flow.service.abstraction.Service;

import java.util.List;

/**
 * Сервис для Организации
 * @author Баратов Руслан
 */
public class OrganizationService implements Service<Organization> {

    private DAO<Organization> repository = new RepositoryXml<>(Organization.class);

    private static OrganizationService organizationService;

    private OrganizationService() {
    }

    /**
     * Сохранить организацию
     * @param object организация
     */
    @Override
    public void save(Organization object) {
        repository.save(object);
    }

    /**
     * Сохранить список организаций
     * @param objects список организаций
     */
    @Override
    public void saveAll(List<Organization> objects) {
        repository.saveAll(objects);
    }

    /**
     * Получить все организации
     * @return список организаций
     */
    @Override
    public List<Organization> getAll() {
        return repository.getAll();
    }

    /**
     * @return синголтон обьект
     */
    public static OrganizationService getInstance() {
        if (organizationService == null) {
            organizationService = new OrganizationService();
        }
        return organizationService;
    }
}
