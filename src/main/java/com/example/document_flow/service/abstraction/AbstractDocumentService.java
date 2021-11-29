package com.example.document_flow.service.abstraction;

import com.example.document_flow.entity.document.Document;

import java.util.List;

public interface AbstractDocumentService extends AbstractService<Document> {

    List<Document> getDocumentByIdAuthor(int id);
}
