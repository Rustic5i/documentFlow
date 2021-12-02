package com.example.document_flow.service.abstraction.document;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.service.abstraction.Service;

import java.util.List;


/**
 * Интерфейс сервис для управления Document
 *
 * @author Баратов Руслан
 */
public interface DocumentService extends Service<Document> {

    /**
     * Получить все документы по автору
     *
     * @param id автора документов
     * @return перечень документов, созданных автором с указанным id
     */
    List<Document> getDocumentByIdAuthor(long id);
}
