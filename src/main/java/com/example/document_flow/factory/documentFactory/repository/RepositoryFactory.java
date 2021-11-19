package com.example.document_flow.factory.documentFactory.repository;

import com.example.document_flow.factory.abstr.DocumentFactory;
import com.example.document_flow.factory.documentFactory.IncomingDocumentFactory;
import com.example.document_flow.factory.documentFactory.OutgoingDocumentFactory;
import com.example.document_flow.factory.documentFactory.TaskDocumentFactory;

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
    private static HashMap<Class, DocumentFactory> factoryHolder = new HashMap<>();

   static  {
        IncomingDocumentFactory factoryIncoming = new IncomingDocumentFactory();
        OutgoingDocumentFactory factoryOutgoing = new OutgoingDocumentFactory();
        TaskDocumentFactory factoryTask = new TaskDocumentFactory();

        factoryHolder.put(factoryIncoming.getDocumentType(), factoryIncoming);
        factoryHolder.put(factoryOutgoing.getDocumentType(), factoryOutgoing);
        factoryHolder.put(factoryTask.getDocumentType(), factoryTask);
    }

    /**
     * Возвращает нужную фабрику в зависимоти от переданного типа документа.
     * @param type Тип документа
     * @return инстанс фабрики
     */
    public static DocumentFactory getFactoryByTypeDocument(Class type) {
        return factoryHolder.get(type);
    }
}
