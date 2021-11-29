package com.example.document_flow.service.implement;

import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.repository.DAO.DAO;
import com.example.document_flow.repository.staff.StaffRepositoryXml;
import com.example.document_flow.service.abstraction.Service;

import java.util.List;

/**
 * Сервис для Организации
 * @author Баратов Руслан
 */
public class OrganizationServiceXml implements Service<Organization> {

    private final DAO<Organization> REPOSITORY = new StaffRepositoryXml<>(Organization.class);

    private static OrganizationServiceXml organizationService;

    private OrganizationServiceXml() {
    }

    /**
     * Сохранить организацию
     * @param object организация
     */
    @Override
    public void save(Organization object) {
        REPOSITORY.save(object);
    }

    /**
     * Сохранить список организаций
     * @param objects список организаций
     */
    @Override
    public void saveAll(List<Organization> objects) {
        REPOSITORY.saveAll(objects);
    }

    /**
     * Получить все организации
     * @return список организаций
     */
    @Override
    public List<Organization> getAll() {
        return REPOSITORY.getAll();
    }

    /**
     * @return синголтон обьект
     */
    public static OrganizationServiceXml getInstance() {
        if (organizationService == null) {
            organizationService = new OrganizationServiceXml();
        }
        return organizationService;
    }
}