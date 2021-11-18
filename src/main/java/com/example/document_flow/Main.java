package com.example.document_flow;

import com.example.document_flow.entity.DocumentTypeEnum.DocumentType;
import com.example.document_flow.factory.factoryDocument.AbstractFactory;
import com.example.document_flow.factory.abstr.Factory;
import com.example.document_flow.factory.factoryDocument.FactoryTask;
import com.example.document_flow.factory.factoryDocument.FactoryСreator;
import com.example.document_flow.myException.DocumentExistsException;
import com.example.document_flow.registry.DocumentHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class.getName());

    private static final int COUNT = 100;

    private static Random random = new Random();

    private static DocumentHolder registry = DocumentHolder.getInstance();

    public static void main(String[] args) {
        //Генерируем рандомные документы
        for (int i = 0; i < COUNT; i++) {
            try {
                int randomEnum = random.nextInt(DocumentType.values().length);
                Factory factory = new FactoryСreator().creatFactory(DocumentType.values()[randomEnum].getType());
                registry.saveDocument(factory.create());
            } catch (DocumentExistsException e) {
                log.warn("Exception ", e);
            }
        }

        AbstractFactory abstractFactory = new FactoryTask();
        abstractFactory.create();

        System.out.println(registry.groupByAuthorToString());
    }
}
