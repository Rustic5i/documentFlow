package com.example.document_flow.service.implement.document;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.repository.absraction.Repository;
import com.example.document_flow.repository.implement.document.DocumentRepositoryJsonImpl;
import com.example.document_flow.service.abstraction.document.DocumentServiceJson;

import java.util.List;
import java.util.Optional;

/**
 * Класс сервис для управления Document в формате json
 *
 * @author Баратов Руслан
 */
public class DocumentServiceJsonImpl implements DocumentServiceJson {

    private static DocumentServiceJsonImpl documentService;

    private final Repository<Document> repository = DocumentRepositoryJsonImpl.getInstance();

    private DocumentServiceJsonImpl() {
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

    /**
     * Сохранить документ
     *
     * @param object документ
     */
    @Override
    public void save(Document object) throws SaveObjectException {
        repository.save(object);
    }

    /**
     * Сохранить список документов
     *
     * @param objects список документов
     */
    @Override
    public void saveAll(List<Document> objects) throws SaveObjectException {
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
     * Удалить объект по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        repository.deleteById(id);
    }

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(Document object) throws SaveObjectException {
        repository.update(object);
    }

    /**
     * Найти объект по id
     *
     * @param id id объекта класса <code>Department</code>
     * @return найденный объект класса <code>Department</code>
     */
    @Override
    public Optional<Document> findById(long id) {
        return repository.findById(id);
    }
}
