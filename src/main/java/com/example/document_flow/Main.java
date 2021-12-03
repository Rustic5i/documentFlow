package com.example.document_flow;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.repository.derby.OrganizationDerbyDataBase;
import com.example.document_flow.repository.derby.PersonDerbyDataBase;

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
//        PersonDerbyDataBase derbyDataBase = PersonDerbyDataBase.getInstance();
//        derbyDataBase.savePerson(dataGenerator.getPerson());
//        derbyDataBase.savePerson(dataGenerator.getPerson());
//        List<Person> personList = derbyDataBase.getAllPerson();
//        derbyDataBase.saveAllPerson(dataGenerator.PERSON_LIST.stream().limit(10).toList());
        //////////////////////createOrganizationTable//////////
        OrganizationDerbyDataBase organizationDerbyDataBase = OrganizationDerbyDataBase.getInstance();
    }
}
