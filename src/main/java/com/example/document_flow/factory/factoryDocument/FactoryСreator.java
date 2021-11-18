package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.abstr.Factory;

/**
 * Фабрика фабрик
 * @author Баратов Руслан
 */
public class FactoryСreator {

    private static DocumentFactoryHolder factoryHolder = new DocumentFactoryHolder();

    /**
     * Создает нужную фабрику в зависимости от переданного типа документа
     * @param type тип документа
     * @return обьект фабрка
     */
    public static Factory creatFactory(Class type) {
        return factoryHolder.getFactoryByTypeDocument(type);
    }
}
