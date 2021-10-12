package com.example.document_flow.model;

import com.example.document_flow.factory.abstr.IDocumentFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class DocumentTest {

    private Set<Document> set = new TreeSet<>();

    @Autowired
    private IDocumentFactory documentFactory;

    @BeforeEach
    public void list(){
//        Random random = new Random();
//        for (int i = 0; i < 5; i++) {
//            Document outgoing = new Document(1l+random.nextLong(20));
//            set.add(outgoing);
//        }
    }

    @Test
    void compareTo() {
        documentFactory.getDocument(Task.class);
        documentFactory.getDocument(Incoming.class);
        documentFactory.getDocument(Outgoing.class);
        documentFactory.getDocument(Document.class);
        ///documentFactory.getDocument(Object.class);
      //  System.out.println(set);

    }
}