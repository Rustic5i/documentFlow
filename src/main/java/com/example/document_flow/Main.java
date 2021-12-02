package com.example.document_flow;

import com.example.document_flow.repository.derby.DepartmentDerbyDataBase;

public class Main {

    public static void main(String[] args) {
//        DocumentRequestHandler requestHandler = new DocumentRequestHandler();
//        requestHandler.setRequest(args);
//
//        DataGenerator dataGenerator = DataGenerator.getInstance();
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

        DepartmentDerbyDataBase derbyDataBase = DepartmentDerbyDataBase.getInstance();
    }
}
