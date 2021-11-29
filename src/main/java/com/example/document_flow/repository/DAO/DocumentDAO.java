package com.example.document_flow.repository.DAO;

import com.example.document_flow.entity.document.Document;

import java.util.List;

public interface DocumentDAO extends DAO<Document> {

    List<Document> getDocumentByIdAuthor(int id);
}
