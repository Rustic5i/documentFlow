package com.example.document_flow;

import com.example.document_flow.factory.abstr.Factory;
import com.example.document_flow.factory.factoriesEnum.DocumentType;
import com.example.document_flow.factory.factoryDocument.FactoryMain;
import com.example.document_flow.myException.DocumentExistsException;
import com.example.document_flow.registry.RegistryDocuments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class.getName());

    private static final int COUNT = 100;

    private static Random random = new Random();

    private static RegistryDocuments registry = RegistryDocuments.getInstance();

    public static void main(String[] args) {
        //Генерируем рандомные документы
        for (int i = 0; i < COUNT; i++) {
            try {
                int randomEnum = random.nextInt(DocumentType.values().length);
                Factory factory = new FactoryMain().creatFactory(DocumentType.values()[randomEnum]);
                registry.saveDocument(factory.createDocument());
            } catch (DocumentExistsException e) {
                log.warn("Exception ", e);
            }
        }

        System.out.println(registry.getAllDocumentToList());
    }
}
