package com.example.document_flow;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.service.abstraction.document.DocumentService;
import com.example.document_flow.service.abstraction.document.DocumentServiceJson;
import com.example.document_flow.service.abstraction.staff.PersonService;
import com.example.document_flow.service.implement.document.DocumentServiceImpl;
import com.example.document_flow.service.implement.document.DocumentServiceJsonImpl;
import com.example.document_flow.service.implement.staff.PersonServiceImpl;
import com.example.document_flow.web.controller.DocumentRequestHandler;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        DocumentRequestHandler requestHandler = new DocumentRequestHandler();
        requestHandler.setRequest(args);

        DataGenerator dataGenerator = DataGenerator.getInstance();

        //Сохраняем 3 работника
        PersonService personService = PersonServiceImpl.getInstance();
        personService.saveAll(dataGenerator.PERSON_LIST.stream().limit(3).toList());
        //Получаем всех трех работников
        System.out.println(personService.getAll());

        DocumentService repositoryDocument = DocumentServiceImpl.getInstance();
        List<Document> personList = repositoryDocument.getAll();

        //////   Сохраняем все сгенерированные документы в JSON
        DocumentServiceJson documentService = DocumentServiceJsonImpl.getInstance();
        documentService.saveAll(personList);

        //Получаем все документы из JSON
        System.out.println(documentService.getAll());
    }
}
