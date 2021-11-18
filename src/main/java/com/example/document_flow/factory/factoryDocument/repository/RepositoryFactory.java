package com.example.document_flow.factory.factoryDocument.repository;

import com.example.document_flow.factory.abstr.Factory;
import com.example.document_flow.factory.factoryDocument.FactoryIncoming;
import com.example.document_flow.factory.factoryDocument.FactoryOutgoing;
import com.example.document_flow.factory.factoryDocument.FactoryTask;

import java.util.HashMap;

/**
 * Представляет из себя репозиторий всех фабрик
 *
 * @author Баратов Руслан
 */
public class RepositoryFactory {

    /**
     * Хранит список всех фабрик.
     * Где ключ - это тип документа на которм специализуется фабрика.
     * Значение - сама фабрика
     */
    private static HashMap<Class, Factory> factoryHolder = new HashMap<>();

   static  {
        FactoryIncoming factoryIncoming = new FactoryIncoming();
        FactoryOutgoing factoryOutgoing = new FactoryOutgoing();
        FactoryTask factoryTask = new FactoryTask();

        factoryHolder.put(factoryIncoming.getDocumentType(), factoryIncoming);
        factoryHolder.put(factoryOutgoing.getDocumentType(), factoryOutgoing);
        factoryHolder.put(factoryTask.getDocumentType(), factoryTask);
    }

    /**
     * Возвращает нужную фабрику в зависимоти от переданного типа документа.
     * @param type Тип документа
     * @return инстанс фабрики
     */
    public static Factory getFactoryByTypeDocument(Class type) {
        return factoryHolder.get(type);
    }
}
