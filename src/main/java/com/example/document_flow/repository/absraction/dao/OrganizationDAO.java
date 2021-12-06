package com.example.document_flow.repository.absraction.dao;

import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.exception.SaveObjectException;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс dao, определяющий методы доступа к данным для экземпляров класса <code>Organization</code>
 *
 * @author Баратов Руслан
 */
public interface OrganizationDAO {

    /**
     * Сохранить объект класса <code>Organization</code>
     *
     * @param organization объект класса <code>Organization</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    void saveOrganization(Organization organization) throws SaveObjectException;

    /**
     * Получить список всех сохраненных объектов класса <code>Organization</code>
     *
     * @return список сохраненных объектов класса <code>Organization</code>
     */
    List<Organization> getAllOrganization();

    /**
     * Сохранить список объектов класса <code>Organization</code>
     *
     * @param organizationList список объектов класса <code>Organization</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    void saveAllOrganization(List<Organization> organizationList) throws SaveObjectException;

    /**
     * Найти объект класса <code>Organization</code> по id
     *
     * @param id id объекта класса <code>Organization</code>
     * @return найденный объект класса <code>Organization</code>
     */
    Optional<Organization> findOrganizationById(long id);
}
