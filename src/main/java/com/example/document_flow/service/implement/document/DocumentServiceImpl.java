package com.example.document_flow.service.implement.document;

import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.repository.implement.document.DocumentRepositoryImpl;
import com.example.document_flow.entity.document.Document;
import com.example.document_flow.service.abstraction.document.DocumentService;

import java.util.List;
import java.util.Optional;

/**
 * Класс сервис для управления Document
 *
 * @author Баратов Руслан
 */
public class DocumentServiceImpl implements DocumentService {

    private static DocumentServiceImpl service;

    private final DocumentRepositoryImpl REPOSITORY = DocumentRepositoryImpl.getInstance();

    private DocumentServiceImpl() {
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
     * Удалить объект по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        REPOSITORY.deleteById(id);
    }

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(Document object) throws SaveObjectException {
        REPOSITORY.update(object);
    }

    /**
     * Найти объект по id
     *
     * @param id id объекта класса <code>Department</code>
     * @return найденный объект класса <code>Department</code>
     */
    @Override
    public Optional<Document> findById(long id) {
        return REPOSITORY.findById(id);
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
}
