package com.example.document_flow;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.repository.implement.derby.DepartmentDerbyDataBase;
import com.example.document_flow.repository.implement.derby.OrganizationDerbyDataBase;
import com.example.document_flow.repository.implement.derby.PersonDerbyDataBase;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        DocumentRequestHandler requestHandler = new DocumentRequestHandler();
//        requestHandler.setRequest(args);
//
        DataGenerator dataGenerator = DataGenerator.getInstance();
//
//        //Сохраняем 3 работника
//        PersonService personService = PersonServiceImpl.getInstance();
//        personService.saveAll(dataGenerator.PERSON_LIST.stream().limit(3).toList());
//        //Получаем всех трех работников
//        System.out.println(personService.getAll());
//
//        DocumentService repositoryDocument = DocumentServiceImpl.getInstance();
//        List<Document> personList = repositoryDocument.getAll();
//
//        //////   Сохраняем все сгенерированные документы в JSON
//        DocumentServiceJson documentService = DocumentServiceJsonImpl.getInstance();
//        documentService.saveAll(personList);
//
//        //Получаем все документы из JSON
//        System.out.println(documentService.getAll());

        //////////////Person/////////////
        PersonDerbyDataBase derbyDataBase = PersonDerbyDataBase.getInstance();
//        derbyDataBase.savePerson(dataGenerator.getPerson());
//        derbyDataBase.savePerson(dataGenerator.getPerson());
        List<Person> personList = derbyDataBase.getAllPerson();
////        derbyDataBase.saveAllPerson(dataGenerator.PERSON_LIST.stream().limit(10).toList());
//        //////////////////////createOrganizationTable//////////
        OrganizationDerbyDataBase organizationDerbyDataBase = OrganizationDerbyDataBase.getInstance();
//        int count = 10;
//        List<Organization> departmentList = new ArrayList<>();
//        for (Person per : personList) {
//            Organization organization = new Organization();
//            organization.setId(count);
//            organization.setShortName("wdawd" + count);
//            organization.setFullName("wdwad" + count);
//            organization.setContactPhoneNumber("12321321" + count);
//            organization.setManager(per);
//            count++;
//            departmentList.add(organization);
//        }
//
//        //organizationDerbyDataBase.saveDepartment(organization);
//        organizationDerbyDataBase.saveAllDepartment(departmentList.stream().limit(5).toList());
//        derbyDataBase.findPersonById(715);
        List<Organization> organizationList1 = organizationDerbyDataBase.getAllOrganization();
        ////////////////////DEPARTMENT/////////////

        DepartmentDerbyDataBase departmentDerbyDataBase = DepartmentDerbyDataBase.getInstance();

        int count = 10;
        List<Department> departmentList = new ArrayList<>();
        for (Person per : personList) {
            Department department = new Department();
            department.setId(count);
            department.setShortName("wdawd" + count);
            department.setFullName("wdwad" + count);
            department.setContactPhoneNumber("12321321" + count);
            department.setManager(per);
            count++;
            departmentList.add(department);
        }
     //   departmentDerbyDataBase.saveAllDepartment(departmentList);
        List<Department> departmentList1 = departmentDerbyDataBase.getAllDepartment();
    }
}
