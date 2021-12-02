package com.example.document_flow.service.implement.document;

import com.example.document_flow.repository.implement.document.DocumentRepositoryImpl;
import com.example.document_flow.entity.document.Document;
import com.example.document_flow.service.abstraction.document.DocumentService;

import java.util.List;

/**
 * Класс сервис для управления Document
 *
 * @author Баратов Руслан
 */
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepositoryImpl REPOSITORY = DocumentRepositoryImpl.getInstance();

    private static DocumentServiceImpl service;

    private DocumentServiceImpl() {
    }

    /**
     * Сохранить документ
     *
     * @param object документ
     */
    @Override
    public void save(Document object) {
        REPOSITORY.save(object);
    }

    /**
     * Сохранить список документов
     *
     * @param objects список документов
     */
    @Override
    public void saveAll(List<Document> objects) {
        REPOSITORY.saveAll(objects);
    }

    /**
     * Получить все документы
     *
     * @return список документов
     */
    @Override
    public List<Document> getAll() {
        return REPOSITORY.getAll();
    }

    /**
     * Получить все документы по автору
     *
     * @param id автора документов
     * @return перечень документов, созданных автором с указанным id
     */
    @Override
    public List<Document> getDocumentByIdAuthor(long id) {
        return REPOSITORY.getDocumentByIdAuthor(id);
    }

    /**
     * @return синголтон обьект
     */
    public static DocumentServiceImpl getInstance() {
        if (service == null) {
            service = new DocumentServiceImpl();
        }
        return service;
    }
}
