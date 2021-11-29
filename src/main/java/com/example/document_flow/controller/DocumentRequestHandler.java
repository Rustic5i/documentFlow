package com.example.document_flow.controller;

import com.example.document_flow.exception.InvalidParametersException;
import com.example.document_flow.generator.DocumentGenerator;
import com.example.document_flow.service.implement.DocumentService;
import com.example.document_flow.validation.ParametersValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Обработчик запросов
 *
 * @author Баратов Руслан
 */
public class DocumentRequestHandler {

    private static DocumentService serviceExpanded = new DocumentService();

    private final Scanner SCANNER = new Scanner(System.in);

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentRequestHandler.class.getName());

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
                LOGGER.info(serviceExpanded.getAll().toString());
            } catch (InvalidParametersException e) {
                LOGGER.warn("Exception ", e);
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
                int count = ParametersValidation.isNumber(SCANNER.nextLine());
                serviceExpanded.saveAll(DocumentGenerator.run(count));
                LOGGER.info(serviceExpanded.getAll().toString());
                break;
            } catch (InvalidParametersException e) {
                LOGGER.warn("Exception ", e);
            }
        }
    }
}
