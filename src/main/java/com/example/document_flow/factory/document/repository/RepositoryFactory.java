package com.example.document_flow.factory.document.repository;

import com.example.document_flow.factory.Factory;
import com.example.document_flow.factory.document.IncomingDocumentFactory;
import com.example.document_flow.factory.document.OutgoingDocumentFactory;
import com.example.document_flow.factory.document.TaskDocumentFactory;

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

    static {
        IncomingDocumentFactory factoryIncoming = new IncomingDocumentFactory();
        OutgoingDocumentFactory factoryOutgoing = new OutgoingDocumentFactory();
        TaskDocumentFactory factoryTask = new TaskDocumentFactory();

        factoryHolder.put(factoryIncoming.getTypeObject(), factoryIncoming);
        factoryHolder.put(factoryOutgoing.getTypeObject(), factoryOutgoing);
        factoryHolder.put(factoryTask.getTypeObject(), factoryTask);
    }

    /**
     * Возвращает нужную фабрику в зависимоти от переданного типа документа.
     *
     * @param type Тип документа
     * @return инстанс фабрики
     */
    public static Factory getFactoryByTypeDocument(Class type) {
        return factoryHolder.get(type);
    }
}
