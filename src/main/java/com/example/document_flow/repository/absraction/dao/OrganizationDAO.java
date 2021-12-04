package com.example.document_flow.repository.absraction.dao;

import com.example.document_flow.entity.staff.Organization;

import java.util.List;

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
     */
    void saveOrganization(Organization organization);

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
     */
    void saveAllOrganization(List<Organization> organizationList);

    /**
     * Найти объект класса <code>Organization</code> по id
     *
     * @param id id объекта класса <code>Organization</code>
     * @return найденный объект класса <code>Organization</code>
     */
    Organization findOrganizationById(long id);
}
