package com.example.document_flow.generator;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.entity.document.DocumentType;
import com.example.document_flow.factory.Factory;
import com.example.document_flow.factory.document.СreatorDocumentFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Генератор документации
 * @author Баратов Руслан
 */
public class DocumentGenerator {

    private static final Random random = new Random();

    private static List<Document> documentList = new ArrayList<>();

    /**
     * Генерирует документы.
     * @param count количество требуемых документов.
     * @return список генерируемых документов.
     */
    public static List<Document> run(int count){
        for (int i = 0; i < count; i++) {
                int randomEnum = random.nextInt(DocumentType.values().length);
                Factory<Document> factory = new СreatorDocumentFactory().creatFactory(DocumentType.values()[randomEnum].getType());
                documentList.add(factory.create());
        }
        return documentList;
    }
}
