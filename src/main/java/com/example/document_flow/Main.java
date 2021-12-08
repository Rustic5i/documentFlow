package com.example.document_flow;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.service.abstraction.document.DocumentService;
import com.example.document_flow.service.abstraction.document.DocumentServiceJson;
import com.example.document_flow.service.abstraction.staff.PersonService;
import com.example.document_flow.service.implement.document.DocumentServiceImpl;
import com.example.document_flow.service.implement.document.DocumentServiceJsonImpl;
import com.example.document_flow.service.implement.staff.xml.PersonServiceXmlImpl;
import com.example.document_flow.web.controller.DocumentRequestHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SaveObjectException, IOException {
        DocumentRequestHandler requestHandler = new DocumentRequestHandler();
        requestHandler.setRequest(args);

        DataGenerator dataGenerator = DataGenerator.getInstance();

        //Сохраняем 3 работника
        PersonService personService = PersonServiceXmlImpl.getInstance();
        personService.saveAll(dataGenerator.PERSON_LIST.stream().limit(10).toList());
        //Получаем всех трех работников
        System.out.println(personService.getAll());

        DocumentService repositoryDocument = DocumentServiceImpl.getInstance();
        List<Document> personList = repositoryDocument.getAll();

        //////   Сохраняем все сгенерированные документы в JSON
        DocumentServiceJson documentService = DocumentServiceJsonImpl.getInstance();
        documentService.saveAll(personList);

        //Получаем все документы из JSON
        System.out.println(documentService.getAll());
        //////////////////////////////////////////////////////////////////////////////////////////


//        PersonDerbyDataBase derbyDataBase = PersonDerbyDataBase.getInstance();
//        derbyDataBase.savePerson(dataGenerator.getPerson());
//        derbyDataBase.savePerson(dataGenerator.getPerson());
//        List<Person> personList11 = derbyDataBase.getAllPerson();
//        derbyDataBase.saveAllPerson(dataGenerator.PERSON_LIST.stream().limit(10).toList());
        //////////////////////createOrganizationTable//////////
//        OrganizationDerbyDataBase organizationDerbyDataBase = OrganizationDerbyDataBase.getInstance();
//        int count = 10;
//        List<Organization> departmentList = new ArrayList<>();
//        for (Person per : personList11) {
//            Organization organization = new Organization();
//            organization.setId(count);
//            organization.setShortName("wdawd" + count);
//            organization.setFullName("wdwad" + count);
//            organization.setContactPhoneNumber("12321321" + count);
//            organization.setManager(per);
//            organizationDerbyDataBase.saveOrganization(organization);
////            count1++;
//            departmentList.add(organization);
//        }

        //organizationDerbyDataBase.saveDepartment(organization);
//        organizationDerbyDataBase.saveAllOrganization(departmentList.stream().limit(5).toList());
//        derbyDataBase.findPersonById(715);
        //     List<Organization> organizationList1 = organizationDerbyDataBase.getAllOrganization();
        ////////////////////DEPARTMENT/////////////
//
//        DepartmentDerbyDataBase departmentDerbyDataBase = DepartmentDerbyDataBase.getInstance();
//
//        int count1 = 10;
//        List<Department> departmentList1 = new ArrayList<>();
//        for (Person per : personList11) {
//            Department department = new Department();
//            department.setId(count1);
//            department.setShortName("wdawd" + count1);
//            department.setFullName("wdwad" + count1);
//            department.setContactPhoneNumber("12321321" + count1);
//            department.setManager(per);
//            count1++;
//            departmentList1.add(department);
//        }
        //   departmentDerbyDataBase.saveAllDepartment(departmentList1);
        // List<Department> departmentList1 = departmentDerbyDataBase.getAllDepartment();
        /////////////TEST PROPERTIES /////////////////
    }

}
