package com.example.document_flow.repository.absraction.document;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.repository.absraction.Repository;

import java.util.List;

/**
 * Интерфейс для объекта доступа к данным Document
 *
 * @author Баратов Руслан
 */
public interface DocumentRepository extends Repository<Document> {

    /**
     * Получить все документы по автору
     *
     * @param id автора документов
     * @return перечень документов, созданных автором с указанным id
     */
    List<Document> getDocumentByIdAuthor(long id);
}
