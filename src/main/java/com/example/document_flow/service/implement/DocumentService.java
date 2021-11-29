package com.example.document_flow.service.implement;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.repository.document.DocumentRepository;
import com.example.document_flow.service.abstraction.AbstractDocumentService;

import java.util.List;

/**
 * Сервис для Документов
 *
 * @author Баратов Руслан
 */
public class DocumentService implements AbstractDocumentService {

    private final DocumentRepository REPOSITORY = DocumentRepository.getInstance();

    private static DocumentService service;

    private DocumentService() {
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
     * @return синголтон обьект
     */
    public static DocumentService getInstance() {
        if (service == null) {
            service = new DocumentService();
        }
        return service;
    }

    @Override
    public List<Document> getDocumentByIdAuthor(int id) {
        return service.getDocumentByIdAuthor(id);
    }
}
