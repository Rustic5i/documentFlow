package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.abstr.Factory;
import com.example.document_flow.factory.factoriesEnum.DocumentType;

/**
 * Фабрика фабрик
 * @author Баратов Руслан
 */
public class FactoryMain{

    /**
     * Создает нужную фабрику в зависимости от переданного типа документа
     * @param type тип документа
     * @return обьект фабрка
     */
    public static Factory creatFactory(DocumentType type) {
        return switch (type){
            case INCOMING -> new FactoryIncoming();
            case OUTGOING -> new FactoryOutgoing();
            case TASK -> new FactoryTask();
        };
    }
}
