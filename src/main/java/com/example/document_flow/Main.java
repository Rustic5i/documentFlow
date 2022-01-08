package com.example.document_flow;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.factory.staff.DepartmentFactory;
import com.example.document_flow.factory.staff.OrganizationFactory;
import com.example.document_flow.repository.implement.staff.DepartmentRepositoryXmlImpl;
import com.example.document_flow.repository.implement.staff.OrganizationRepositoryXmlImpl;
import com.example.document_flow.repository.implement.staff.PersonRepositoryXmlImpl;
import com.example.document_flow.web.controller.DocumentRequestHandler;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SaveObjectException {
        DocumentRequestHandler requestHandler = new DocumentRequestHandler();
        requestHandler.setRequest(args);

        OrganizationFactory organizationFactory = OrganizationFactory.getInstance();
        List<Organization> organizationList = new ArrayList<>();

        DepartmentFactory departmentFactory = DepartmentFactory.getInstance();
        List<Department> departmentList = new ArrayList<>();

        PersonRepositoryXmlImpl personRepositoryXml = PersonRepositoryXmlImpl.getInstance();
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            personList.add(DataGenerator.getInstance().getPerson());
        }
      //  personRepositoryXml.saveAll(personList);

        for (int i = 0; i < 4; i++) {
            Organization organization = organizationFactory.create();
            organizationList.add(organization);

            Department department = departmentFactory.create();
            departmentList.add(department);
        }

        OrganizationRepositoryXmlImpl organizationRepositoryXml = OrganizationRepositoryXmlImpl.getInstance();
        organizationRepositoryXml.saveAll(organizationList);

        DepartmentRepositoryXmlImpl departmentRepositoryXml = DepartmentRepositoryXmlImpl.getInstance();
        departmentRepositoryXml.saveAll(departmentList);

        personRepositoryXml.saveAll(personList);
    }
}
