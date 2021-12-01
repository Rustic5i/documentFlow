package com.example.document_flow;

import com.example.document_flow.controller.DocumentRequestHandler;
import com.example.document_flow.entity.document.Document;
import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.service.implement.DocumentService;
import com.example.document_flow.service.implement.DocumentServiceJson;
import com.example.document_flow.service.implement.PersonService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        DocumentRequestHandler requestHandler = new DocumentRequestHandler();
        requestHandler.setRequest(args);

        DataGenerator dataGenerator = DataGenerator.getInstance();
        //Сохраняем 100 работника
        PersonService personService = PersonService.getInstance();
        personService.saveAll(dataGenerator.PERSON_LIST.stream().limit(100).toList());

        DocumentService repositoryDocument = DocumentService.getInstance();
        List<Document> personList = repositoryDocument.getAll();

        //////   Сохраняем все сгенерированные документы в JSON
        DocumentServiceJson documentService = DocumentServiceJson.getInstance();
        documentService.saveAll(personList);
    }
}
