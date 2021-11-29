package com.example.document_flow;

import com.example.document_flow.controller.DocumentRequestHandler;
import com.example.document_flow.entity.document.Document;
import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.service.implement.DocumentService;
import com.example.document_flow.service.implement.DocumentServiceJson;
import com.example.document_flow.service.implement.PersonServiceXml;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        DocumentRequestHandler requestHandler = new DocumentRequestHandler();
        requestHandler.setRequest(args);

        DataGenerator dataGenerator = DataGenerator.getInstance();
        //Сохраняем 3 работника
        PersonServiceXml personService = PersonServiceXml.getInstance();
        personService.saveAll(dataGenerator.personList.stream().limit(3).toList());
        //Получаем всех трех работников
        System.out.println(personService.getAll());

        DocumentService repositoryDocument = new DocumentService();
        List<Document> personList = repositoryDocument.getAll();

        //////   Сохраняем все сгенерированные документы в JSON
        DocumentServiceJson documentService = DocumentServiceJson.getInstance();
        documentService.saveAll(personList);

        //Получаем все документы из JSON
        System.out.println(documentService.getAll());
    }
}
