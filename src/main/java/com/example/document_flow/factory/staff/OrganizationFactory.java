package com.example.document_flow.factory.staff;

import com.example.document_flow.entity.staff.Organization;

public class OrganizationFactory extends AbstractStaffFactory<Organization>{
    @Override
    public Class getTypeObject() {
        return Organization.class;
    }

    @Override
    Organization createInstance() {
        return new Organization();
    }

    @Override
    void fillAdditionalFields(Organization staff) {
        staff.setFullName(getDataGenerator().getNamesOrganization().getFullName());
        staff.setShortName(getDataGenerator().getNamesOrganization().getShortName());
        staff.setManager(getDataGenerator().getPerson());
        staff.setContactPhoneNumber(getDataGenerator().gePhoneNumber());
    }
}
