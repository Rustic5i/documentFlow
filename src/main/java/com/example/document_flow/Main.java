package com.example.document_flow;

import com.example.document_flow.controller.RequestHandler;
import com.example.document_flow.entity.document.Document;
import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.service.implement.DocumentService;
import com.example.document_flow.service.implement.DocumentServiceExpanded;
import com.example.document_flow.service.implement.PersonService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        RequestHandler requestHandler = new RequestHandler();
        requestHandler.setRequest(args);

        DataGenerator dataGenerator = DataGenerator.getInstance();
        //Сохраняем 3 работника
        PersonService personService = PersonService.getInstance();
        personService.saveAll(dataGenerator.personList.stream().limit(3).toList());
        //Получаем всех трех работников
        System.out.println(personService.getAll());

        DocumentServiceExpanded repositoryDocument = new DocumentServiceExpanded();
        List<Document> personList = repositoryDocument.getAll();

        //////   Сохраняем все сгенерированные документы в JSON
        DocumentService documentService = DocumentService.getInstance();
        documentService.saveAll(personList);

        //Получаем все документы из JSON
        System.out.println(documentService.getAll());
    }
}
