package com.example.document_flow.service.implement.document;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.repository.absraction.Repository;
import com.example.document_flow.repository.implement.document.DocumentRepositoryJsonImpl;
import com.example.document_flow.service.abstraction.Service;

import java.util.List;

/**
 * Класс сервис для управления Document в формате json
 *
 * @author Баратов Руслан
 */
public class DocumentServiceJsonImpl implements Service<Document> {

    private final Repository<Document> REPOSITORY = DocumentRepositoryJsonImpl.getInstance();

    private static DocumentServiceJsonImpl documentService;

    private DocumentServiceJsonImpl() {
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
    public static DocumentServiceJsonImpl getInstance() {
        if (documentService == null) {
            documentService = new DocumentServiceJsonImpl();
        }
        return documentService;
    }
}
