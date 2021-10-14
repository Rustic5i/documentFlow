package com.example.document_flow;

import com.example.document_flow.factory.generate.DocumentFactory;
import com.example.document_flow.model.*;
import com.example.document_flow.model.person.Person;
import com.example.document_flow.util.Grouper;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class Main {

    private ArrayList<Document> documentList = new ArrayList<>();

    private Map<Person, List<Document>> documentByAuthor = new HashMap<>();

    private DocumentFactory documentFactory = new DocumentFactory();

    private static final int count = 20;

    private Random random = new Random();

    @PostConstruct
    public void main() {

        //Генерируем рандомные документы
        for (int i = 0; i < count; i++) {
            Document document = documentFactory
                    .getDocument(DocumentTypes.values()[random.nextInt(DocumentTypes.values().length)]);
            if (document != null) {
                documentList.add(document);
            }
        }
        // Группируем Документы по автору
        Grouper grouper = new Grouper();
        grouper.groupByAuthor(documentList);
        System.out.println(grouper);
    }
}
