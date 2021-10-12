package com.example.document_flow;

import com.example.document_flow.factory.DocumentFactory;
import com.example.document_flow.model.*;
import com.example.document_flow.model.person.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;

@SpringBootApplication
public class DocumentFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentFlowApplication.class, args);

        ArrayList<Document> documentList = new ArrayList<>();

        Map<Person, List<Document>> documentByAuthor = new HashMap<>();

        DocumentFactory documentFactory = new DocumentFactory();

        //Генерируем документы
        for (int i = 0; i < 5; i++) {
            Document incoming = documentFactory.getDocument(Incoming.class);
            if (incoming != null) {
                documentList.add(incoming);
            }
            Document outgoing = documentFactory.getDocument(Outgoing.class);
            if (outgoing != null) {
                documentList.add(outgoing);
            }
            Document task = documentFactory.getDocument(Task.class);
            if (task != null) {
                documentList.add(task);
            }
        }

        // Группируем по Документы по автору
        for (Document d : documentList) {
            if (documentByAuthor.containsKey(d.getAuthor())) {
                documentByAuthor.get(d.getAuthor()).add(d);
            } else {
                documentByAuthor.put(d.getAuthor(), new ArrayList<>());
                documentByAuthor.get(d.getAuthor()).add(d);
            }
        }

        for (Map.Entry<Person, List<Document>> entry : documentByAuthor.entrySet()) {
            System.out.println(entry.getKey()+":");
            for (Document d: entry.getValue()) {
                System.out.println(" * "+d.toString());
            }
        }
    }
}
