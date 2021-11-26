package com.example.document_flow.service.abstraction;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.entity.staff.Person;

import java.util.List;
import java.util.Map;

public interface ServiceExpanded extends Service<Document> {
    Map<Person, List<Document>> groupByAuthor();
}
