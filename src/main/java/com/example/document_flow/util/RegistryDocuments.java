package com.example.document_flow.util;

import com.example.document_flow.entity.Document;
import com.example.document_flow.myException.DocumentExistsException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Предстовляет из себя реест по хранения всех созданных документов
 */
public class RegistryDocuments {

    /**
     * Хранит все созданные документы.
     * Где <code>Long</code> это ключ, хранит в себе уникальный регистрационный номер документа
     * <code>Document</code> хранит созданный документ
     */
    private static Map<Long, Document> documentMap = new HashMap<>();

    private static Grouper grouper = new Grouper();

    private static RegistryDocuments registryDocuments;

    private RegistryDocuments() {
    }

    /**
     * Метод для регистрации созданных документов
     *
     * @param document документ для регистрации
     * @throws DocumentExistsException если документ с таким регистрационным номером уже был создан ранее
     */
    public void registerDocument(Document document) throws DocumentExistsException {
        Long registrationNumber = document.getRegistrationNumber();
        if (documentMap.containsKey(registrationNumber)) {
            throw new DocumentExistsException("Document с регистрационным номер " + registrationNumber + " уже существует ");
        }
        documentMap.put(registrationNumber, document);
    }

    public static List<Document> getAllDocument() {
        return documentMap.values().stream().toList();
    }

    public String report() {
        grouper.groupByAuthor(getAllDocument());
        return grouper.report();
    }

    /**
     * @return синголтон обьект
     */
    public static RegistryDocuments getInstance() {
        if (registryDocuments == null) {
            registryDocuments = new RegistryDocuments();
        }
        return registryDocuments;
    }
}
