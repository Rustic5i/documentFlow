package com.example.document_flow.controller;

import com.example.document_flow.generator.DocumentGenerator;
import com.example.document_flow.exception.InvalidParametersException;
import com.example.document_flow.repository.document.DocumentRepository;
import com.example.document_flow.service.abstraction.Service;
import com.example.document_flow.service.implement.DocumentService;
import com.example.document_flow.service.implement.DocumentServiceExpanded;
import com.example.document_flow.validation.ParametersValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Обработчик запросов
 *
 * @author Баратов Руслан
 */
public class RequestHandler {

    //private static final DocumentRepository repository = DocumentRepository.getInstance();

    private static DocumentServiceExpanded serviceExpanded = new DocumentServiceExpanded();

    private final Scanner scanner = new Scanner(System.in);

    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class.getName());

    public void setRequest(String[] args) {
        handler(args);
    }

    /**
     * Обработчик запроса.
     * Если обработать запрос не получилось, переходим к другому обработчику
     *
     * @param args запрос
     */
    private void handler(String[] args) {
        if (args.length != 0) {
            try {
                int count = ParametersValidation.isNumber(args[0]);
                serviceExpanded.saveAll(DocumentGenerator.run(count));
                System.out.println(serviceExpanded.getAll().toString());
            } catch (InvalidParametersException e) {
                log.warn("Exception ", e);
                handler();
            }
        } else {
            handler();
        }
    }

    /**
     * Обработчик запроса.
     */
    private void handler() {
        while (true) {
            try {
                System.out.println("Введите количество требуемых документов");
                int count = ParametersValidation.isNumber(scanner.nextLine());
                serviceExpanded.saveAll(DocumentGenerator.run(count));
                System.out.println(serviceExpanded.getAll());
                break;
            } catch (InvalidParametersException e) {
                log.warn("Exception ", e);
            }
        }
    }
}
