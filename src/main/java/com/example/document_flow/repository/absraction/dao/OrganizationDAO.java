package com.example.document_flow.repository.absraction.dao;

import com.example.document_flow.entity.staff.Organization;

import java.util.List;

public interface OrganizationDAO {

    void saveOrganization(Organization organization);

    List<Organization> getAllOrganization();

    void saveAllOrganization(List<Organization> organizationList);

    Organization getOrganizationById(long id);
}
