package com.example.document_flow.service.implement.staff.derby;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.DAO.implement.derby.OrganizationDerbyDataBase;
import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.service.abstraction.staff.OrganizationService;

import java.util.List;
import java.util.Optional;

/**
 * Сервисный слой для управления <code>Organization</code> в Derby Data base
 *
 * @author Баратов Руслан
 */
public class OrganizationServiceDerby implements OrganizationService {

    private final DAOCrud<Organization> DAO = OrganizationDerbyDataBase.getInstance();

    private static OrganizationServiceDerby organizationService;

    public OrganizationServiceDerby() {
    }

    /**
     * Сохранить объект класса <code>Organization</code>
     *
     * @param object объект, который требуется сохранить
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void save(Organization object) throws SaveObjectException {
        DAO.save(object);
    }

    /**
     * Сохранить список объектов класса <code>Organization</code>
     *
     * @param objects список объектов класса <code>Organization</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void saveAll(List<Organization> objects) throws SaveObjectException {
        DAO.saveAll(objects);
    }

    /**
     * Получить список всех сохраненных объектов класса <code>Organization</code>
     *
     * @return список сохраненных объектов класса <code>Organization</code>
     */
    @Override
    public List<Organization> getAll() {
        return DAO.getAll();
    }

    /**
     * Найти объект класса <code>Organization</code> по id
     *
     * @param id id объекта класса <code>Organization</code>
     * @return найденный объект класса <code>Organization</code>
     */
    @Override
    public Optional<Organization> findById(long id) {
        return DAO.findById(id);
    }

    /**
     * Удалить объект по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        DAO.deleteById(id);
    }

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(Organization object) throws SaveObjectException {
        DAO.update(object);
    }

    /**
     * @return синголтон обьект
     */
    public static OrganizationServiceDerby getInstance() {
        if (organizationService == null) {
            organizationService = new OrganizationServiceDerby();
        }
        return organizationService;
    }
}
