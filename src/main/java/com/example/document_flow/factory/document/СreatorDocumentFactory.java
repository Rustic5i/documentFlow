package com.example.document_flow.factory.document;

import com.example.document_flow.factory.Factory;
import com.example.document_flow.factory.document.repository.RepositoryFactory;

/**
 * Фабрика фабрик
 * @author Баратов Руслан
 */
public class СreatorDocumentFactory {

    private static RepositoryFactory factoryHolder = new RepositoryFactory();

    /**
     * Создает нужную фабрику в зависимости от переданного типа документа
     * @param type тип документа
     * @return обьект фабрка
     */
    public static Factory createFactory(Class type) {
        return factoryHolder.getFactoryByTypeDocument(type);
    }
}
