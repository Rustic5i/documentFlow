package com.example.document_flow.repository.DAO;

import com.example.document_flow.entity.document.Document;

import java.util.List;

/**
 * Список общих методов для взаимодействия с базой данных/репозиторий
 *
 * @author Баратов Руслан
 */
public interface DocumentDAO extends DAO<Document> {

    /**
     * Получить все документы по автору
     *
     * @param id автора документов
     * @return перечень документов, созданных автором с указанным id
     */
    List<Document> getDocumentByIdAuthor(long id);
}
