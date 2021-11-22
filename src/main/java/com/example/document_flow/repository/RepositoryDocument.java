package com.example.document_flow.repository;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.myException.DocumentExistsException;
import com.example.document_flow.util.DocumentGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Предстовляет из себя репозиторий по хранения всех созданных документов
 *
 * @author Баратов Руслан
 */
public class RepositoryDocument {

    /**
     * Хранит все созданные документы.
     * Где <code>Long</code> это ключ, хранит в себе уникальный регистрационный номер документа
     * <code>Document</code> хранит созданный документ
     */
    private static Map<Long, Document> documentMap = new HashMap<>();

    private static DocumentGroupService grouper = new DocumentGroupService();

    private static final Logger log = LoggerFactory.getLogger(RepositoryDocument.class.getName());

    private static RepositoryDocument registryDocuments;

    private RepositoryDocument() {
    }

    /**
     * Метод для регистрации созданных документов
     *
     * @param document документ для регистрации
     * @throws DocumentExistsException если документ с таким регистрационным номером уже был создан ранее
     */
    public void saveDocument(Document document) throws DocumentExistsException {
        Long registrationNumber = document.getRegistrationNumber();
        containsRegistrationNumber(registrationNumber);
        documentMap.put(registrationNumber, document);
    }

    /**
     * Перегруженный метод <code>saveDocument()</code>
     * Метод для регистрации созданных документов
     *
     * @param document список документов для регистрации
     */
    public void saveDocument(List<Document> document) {
        for (Document doc : document) {
            Long registrationNumber = doc.getRegistrationNumber();
            try {
                containsRegistrationNumber(registrationNumber);
            } catch (DocumentExistsException e) {
                log.warn("Exception ", e);
            }
            documentMap.put(registrationNumber, doc);
        }
    }

    /**
     * @return Возвращает строку, состоящий из значение полей документов, сгруппированные по автору
     */
    public String groupByAuthorToString() {
        grouper.groupByAuthor(documentMap.values().stream().toList());
        return grouper.groupByAuthorToString();
    }

    /**
     * @return Возвращает мапу, сгруппированные по автору
     */
    public Map<Person, List<Document>> groupByAuthor(){
        return grouper.groupByAuthor(documentMap.values().stream().toList());
    }

    /**
     * @return Получить список всех документов
     */
    public List<Document> getAllDocument() {
        return documentMap.values().stream().toList();
    }

    /**
     * Получить документ по регистрационному номеру
     *
     * @param registrationNumber регистрационный номер документа
     * @return найденный документ
     */
    public Document getDocumentByRegNumber(Long registrationNumber) {
        return documentMap.get(registrationNumber);
    }

    /**
     * Выбрасывает исключение DocumentExistsException,
     * если в RepositoryDocument уже храниться документ с таким регистрациионым номером.
     *
     * @param registrationNumber регистрационый номер документа
     * @throws DocumentExistsException если документ с таким регистрационным номером уже был создан ранее
     */
    public void containsRegistrationNumber(Long registrationNumber) throws DocumentExistsException {
        if (documentMap.containsKey(registrationNumber)) {
            throw new DocumentExistsException("Document с регистрационным номер " + registrationNumber + " уже существует ");
        }
    }

    /**
     * @return синголтон обьект
     */
    public static RepositoryDocument getInstance() {
        if (registryDocuments == null) {
            registryDocuments = new RepositoryDocument();
        }
        return registryDocuments;
    }
}
