package com.example.document_flow.generator;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.entity.document.DocumentType;
import com.example.document_flow.factory.Factory;
import com.example.document_flow.factory.document.CreatorDocumentFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Генератор документации
 *
 * @author Баратов Руслан
 */
public class DocumentGenerator {

    private static final Random RANDOM = new Random();

    private static final List<Document> DOCUMENT_LIST = new ArrayList<>();

    /**
     * Генерирует документы.
     *
     * @param count количество требуемых документов.
     * @return список генерируемых документов.
     */
    public static List<Document> run(int count) {
        for (int i = 0; i < count; i++) {
            int randomEnum = RANDOM.nextInt(DocumentType.values().length);
            Factory<Document> factory = new CreatorDocumentFactory().createFactory(DocumentType.values()[randomEnum].getType());
            DOCUMENT_LIST.add(factory.create());
        }
        return DOCUMENT_LIST;
    }
}
