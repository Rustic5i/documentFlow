package com.example.document_flow;

import com.example.document_flow.web.controller.DocumentRequestHandler;
import com.example.document_flow.entity.document.Document;
import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.service.implement.document.DocumentServiceImpl;
import com.example.document_flow.service.implement.document.DocumentServiceJsonImpl;
import com.example.document_flow.service.implement.staff.PersonServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        DocumentRequestHandler requestHandler = new DocumentRequestHandler();
        requestHandler.setRequest(args);

        DataGenerator dataGenerator = DataGenerator.getInstance();
        //Сохраняем 100 работника
        PersonServiceImpl personService = PersonServiceImpl.getInstance();
        personService.saveAll(dataGenerator.PERSON_LIST.stream().limit(100).toList());

        DocumentServiceImpl repositoryDocument = DocumentServiceImpl.getInstance();
        List<Document> personList = repositoryDocument.getAll();

        //////   Сохраняем все сгенерированные документы в JSON
        DocumentServiceJsonImpl documentService = DocumentServiceJsonImpl.getInstance();
        documentService.saveAll(personList);
    }
}
