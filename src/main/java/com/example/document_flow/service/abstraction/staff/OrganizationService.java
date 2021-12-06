package com.example.document_flow.service.abstraction.staff;

import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.service.abstraction.Service;

import java.util.Optional;

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
    Optional<Organization> findOrganizationById(long id);
}
