package com.example.document_flow.factory.staff;

import com.example.document_flow.entity.staff.Department;

public class DepartmentFactory extends AbstractStaffFactory<Department>{

    @Override
    Department createInstance() {
        return new Department();
    }

    @Override
    void fillAdditionalFields(Department staff) {
        staff.setFullName(getDataGenerator().getNamesDepartment().getFullName());
        staff.setShortName(getDataGenerator().getNamesDepartment().getShortName());
        staff.setContactPhoneNumber(getDataGenerator().gePhoneNumber());
        staff.setManager(getDataGenerator().getPerson());
    }

    @Override
    public Class getTypeObject() {
        return Department.class;
    }
}
