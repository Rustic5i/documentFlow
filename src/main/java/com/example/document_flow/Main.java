package com.example.document_flow;

import com.example.document_flow.factory.factoriesEnum.FactoriesEnum;
import com.example.document_flow.factory.factoryDocument.FactoryTask;
import com.example.document_flow.model.Document;
import com.example.document_flow.myException.DocumentExistsException;
import com.example.document_flow.util.Grouper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    private static ArrayList<Document> documentList = new ArrayList<>();

    private static final Logger log = LoggerFactory.getLogger(Main.class.getName());

    private static final int COUNT = 100;

    private static Random random = new Random();

    private static FactoryTask factoryTask = new FactoryTask();

    public static void main(String[] args) {
        //Генерируем рандомные документы
        for (int i = 0; i < COUNT; i++) {
            Document document;
            try {
                document = FactoriesEnum.values()[random.nextInt(FactoriesEnum.values().length)].getFactoryDocument().creatDocument();
                documentList.add(document);
            } catch (DocumentExistsException e) {
                log.warn("Exception ", e);
            }
        }

        // Группируем Документы по автору
        Grouper grouper = new Grouper();
        grouper.groupByAuthor(documentList);
        System.out.println(grouper);

    }
}
