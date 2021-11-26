package com.example.document_flow.repository.document;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.exception.DocumentExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Репозиторий по хранения всех созданных документов
 *
 * @author Баратов Руслан
 */
public class DocumentRepository {

    private static DocumentRepository registryDocuments;

    private DocumentRepository() {
    }

    /**
     * Хранит все созданные документы.
     * Где <code>Long</code> это ключ, хранит в себе уникальный регистрационный номер документа
     * <code>Document</code> хранит созданный документ
     */
    private static Map<Long, Document> documentMap = new HashMap<>();

    private static final Logger log = LoggerFactory.getLogger(DocumentRepository.class.getName());

    List<Document> documentList = new ArrayList<>() {
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            StringBuilder author = new StringBuilder();
            forEach((value) -> {
                if (!(value.getAuthor().toString().equals(author.toString()))) {
                    author.delete(0, 100);
                    author.append(value.getAuthor());
                    sb.append(value.getAuthor()).append(":").append("\n");
                } else {
                    sb.append(" * ").append(value).append("\n");
                }
            });
            return sb.toString();
        }
    };

    /**
     * Метод для регистрации созданных документов
     *
     * @param document документ для регистрации
     * @throws DocumentExistsException если документ с таким регистрационным номером уже был создан ранее
     */
    public void save(Document document) throws DocumentExistsException {
        Long registrationNumber = document.getRegistrationNumber();
        containsRegistrationNumber(registrationNumber);
        documentMap.put(registrationNumber, document);
    }

    /**
     * Метод для регистрации созданных документов
     *
     * @param document список документов для регистрации
     */
    public void saveAll(List<Document> document) {
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
     * @return Получить список всех документов
     */
    public List<Document> getAll() {
        documentList.addAll(documentMap.values().stream().toList());
        Collections.sort(documentList);
        return documentList;
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
     * если в DocumentRepository уже храниться документ с таким регистрационным номером.
     *
     * @param registrationNumber регистрационный номер документа
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
    public static DocumentRepository getInstance() {
        if (registryDocuments == null) {
            registryDocuments = new DocumentRepository();
        }
        return registryDocuments;
    }
}
