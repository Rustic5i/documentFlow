package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.abstr.Factory;
import com.example.document_flow.factory.factoryDocument.repository.RepositoryFactory;

/**
 * Фабрика фабрик
 * @author Баратов Руслан
 */
public class FactoryСreator {

    private static RepositoryFactory factoryHolder = new RepositoryFactory();

    /**
     * Создает нужную фабрику в зависимости от переданного типа документа
     * @param type тип документа
     * @return обьект фабрка
     */
    public static Factory creatFactory(Class type) {
        return factoryHolder.getFactoryByTypeDocument(type);
    }
}
