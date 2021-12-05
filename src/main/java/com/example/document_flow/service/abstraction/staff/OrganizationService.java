package com.example.document_flow.service.abstraction.staff;

import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.service.abstraction.Service;

/**
 * Интерфейс сервис для управления Organization
 *
 * @author Баратов Руслан
 */
public interface OrganizationService extends Service<Organization> {

    /**
     * Найти объект класса <code>Organization</code> по id
     *
     * @param id id объекта класса <code>Organization</code>
     * @return найденный объект класса <code>Organization</code>
     */
    Organization findOrganizationById(long id);
}
