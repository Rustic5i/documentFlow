package com.example.document_flow;

import com.example.document_flow.controller.RequestHandler;
import com.example.document_flow.entity.document.Document;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.repository.document.RepositoryDocument;
import com.example.document_flow.service.implement.DocumentService;
import com.example.document_flow.service.implement.PersonService;

import java.util.List;
import java.util.Map;

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


        RepositoryDocument repositoryDocument = RepositoryDocument.getInstance();
        Map<Person, List<Document>> personListMap = repositoryDocument.groupByAuthor();

        //////   Сохраняем все сгенерированные документы в JSON
        DocumentService documentService = DocumentService.getInstance();
        personListMap.values().forEach(documentService::saveAll);

        System.out.println(documentService.getAll());
        //Получаем все документы из JSON
        System.out.println(documentService.getAll());

    }
}
