package com.example.document_flow.service.implement.staff.derby;

import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.repository.absraction.dao.OrganizationDAO;
import com.example.document_flow.repository.implement.derby.OrganizationDerbyDataBase;
import com.example.document_flow.service.abstraction.staff.OrganizationService;

import java.util.List;
import java.util.Optional;

/**
 * Сервисный слой для управления <code>Organization</code> в Derby Data base
 *
 * @author Баратов Руслан
 */
public class OrganizationServiceDerby implements OrganizationService {

    private final OrganizationDAO DAO = OrganizationDerbyDataBase.getInstance();

    /**
     * Сохранить объект класса <code>Organization</code>
     *
     * @param object объект, который требуется сохранить
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void save(Organization object) throws SaveObjectException {
        DAO.saveOrganization(object);
    }

    /**
     * Сохранить список объектов класса <code>Organization</code>
     *
     * @param objects список объектов класса <code>Organization</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void saveAll(List<Organization> objects) throws SaveObjectException {
        DAO.saveAllOrganization(objects);
    }

    /**
     * Получить список всех сохраненных объектов класса <code>Organization</code>
     *
     * @return список сохраненных объектов класса <code>Organization</code>
     */
    @Override
    public List<Organization> getAll() {
        return DAO.getAllOrganization();
    }

    /**
     * Найти объект класса <code>Organization</code> по id
     *
     * @param id id объекта класса <code>Organization</code>
     * @return найденный объект класса <code>Organization</code>
     */
    @Override
    public Optional<Organization> findOrganizationById(long id) {
        return DAO.findOrganizationById(id);
    }
}
