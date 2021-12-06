package com.example.document_flow.service.implement.staff.xml;

import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.repository.absraction.staff.OrganizationRepository;
import com.example.document_flow.repository.implement.staff.OrganizationRepositoryXmlImpl;
import com.example.document_flow.service.abstraction.staff.OrganizationService;

import java.util.List;
import java.util.Optional;

/**
 * Класс сервис для управления Organization
 *
 * @author Баратов Руслан
 */
public class OrganizationServiceXmlImpl implements OrganizationService {

    private final OrganizationRepository REPOSITORY = OrganizationRepositoryXmlImpl.getInstance();

    private static OrganizationServiceXmlImpl organizationService;

    private OrganizationServiceXmlImpl() {
    }

    /**
     * Сохранить организацию
     *
     * @param object организация
     */
    @Override
    public void save(Organization object) {
        REPOSITORY.save(object);
    }

    /**
     * Сохранить список организаций
     *
     * @param objects список организаций
     */
    @Override
    public void saveAll(List<Organization> objects) {
        REPOSITORY.saveAll(objects);
    }

    /**
     * Получить все организации
     *
     * @return список организаций
     */
    @Override
    public List<Organization> getAll() {
        return REPOSITORY.getAll();
    }

    /**
     * Найти объект класса <code>Organization</code> по id
     *
     * @param id id объекта класса <code>Organization</code>
     * @return найденный объект класса <code>Organization</code>
     */
    @Override
    public Optional<Organization> findOrganizationById(long id) {
        return REPOSITORY.findById(id);
    }

    /**
     * @return синголтон обьект
     */
    public static OrganizationServiceXmlImpl getInstance() {
        if (organizationService == null) {
            organizationService = new OrganizationServiceXmlImpl();
        }
        return organizationService;
    }
}
