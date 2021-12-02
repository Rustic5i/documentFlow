package com.example.document_flow.service.implement;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.repository.DAO.Repository;
import com.example.document_flow.repository.document.DocumentRepositoryJson;
import com.example.document_flow.service.abstraction.Service;

import java.util.List;

/**
 * Сервис для документов
 *
 * @author Баратов Руслан
 */
public class DocumentServiceJson implements Service<Document> {

    private final Repository<Document> REPOSITORY = new DocumentRepositoryJson();

    private static DocumentServiceJson documentService;

    private DocumentServiceJson() {
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
    public static DocumentServiceJson getInstance() {
        if (documentService == null) {
            documentService = new DocumentServiceJson();
        }
        return documentService;
    }
}
