package com.example.document_flow.service.implement;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.repository.DAO.DAO;
import com.example.document_flow.repository.document.DocumentRepositoryJson;
import com.example.document_flow.service.abstraction.Service;

import java.util.List;

/**
 * Сервис для документов
 *
 * @author Баратов Руслан
 */
public class DocumentService implements Service<Document> {

    private DAO<Document> repository = new DocumentRepositoryJson();

    private static DocumentService documentService;

    private DocumentService() {
    }

    /**
     * Сохранить документ
     *
     * @param object документ
     */
    @Override
    public void save(Document object) {
        repository.save(object);
    }

    /**
     * Сохранить список документов
     *
     * @param objects список документов
     */
    @Override
    public void saveAll(List<Document> objects) {
        repository.saveAll(objects);
    }

    /**
     * Получить все документы
     *
     * @return список документов
     */
    @Override
    public List<Document> getAll() {
        return repository.getAll();
    }

    /**
     * @return синголтон обьект
     */
    public static DocumentService getInstance() {
        if (documentService == null) {
            documentService = new DocumentService();
        }
        return documentService;
    }
}
