package com.example.document_flow.util;

import com.example.document_flow.model.Document;
import com.example.document_flow.model.person.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grouper {

    private Map<Person, List<Document>> documentByAuthor = new HashMap<>();

    public Map<Person, List<Document>> groupByAuthor(ArrayList<Document> documentList) {
        for (Document d : documentList) {
            if (documentByAuthor.containsKey(d.getAuthor())) {
                documentByAuthor.get(d.getAuthor()).add(d);
            } else {
                documentByAuthor.put(d.getAuthor(), new ArrayList<>());
                documentByAuthor.get(d.getAuthor()).add(d);
            }
        }
        return documentByAuthor;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<Person, List<Document>> entry : documentByAuthor.entrySet()) {
            str.append(entry.getKey()).append(":").append("\n");
            for (Document d : entry.getValue()) {
                str.append(" * ").append(d.toString()).append("\n");
            }
        }
        return str.toString();
    }
}
