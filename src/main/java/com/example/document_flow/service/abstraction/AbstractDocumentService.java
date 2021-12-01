package com.example.document_flow.service.abstraction;

import com.example.document_flow.entity.document.Document;

import java.util.List;


/**
 * Сервис
 * список общих методов для взаимодействия с сервисом
 *
 * @author Баратов Руслан
 */
public interface AbstractDocumentService extends AbstractService<Document> {

    /**
     * Получить все документы по автору
     *
     * @param id автора документов
     * @return перечень документов, созданных автором с указанным id
     */
    List<Document> getDocumentByIdAuthor(long id);
}
