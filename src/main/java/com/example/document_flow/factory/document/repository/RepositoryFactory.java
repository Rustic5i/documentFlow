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
    private static final HashMap<Class, Factory> FACTORY_HOLDER = new HashMap<>();

    static {
        IncomingDocumentFactory factoryIncoming = new IncomingDocumentFactory();
        OutgoingDocumentFactory factoryOutgoing = new OutgoingDocumentFactory();
        TaskDocumentFactory factoryTask = new TaskDocumentFactory();

        FACTORY_HOLDER.put(factoryIncoming.getTypeObject(), factoryIncoming);
        FACTORY_HOLDER.put(factoryOutgoing.getTypeObject(), factoryOutgoing);
        FACTORY_HOLDER.put(factoryTask.getTypeObject(), factoryTask);
    }

    /**
     * Возвращает нужную фабрику в зависимоти от переданного типа документа.
     *
     * @param type Тип документа
     * @return инстанс фабрики
     */
    public static Factory getFactoryByTypeDocument(Class type) {
        return FACTORY_HOLDER.get(type);
    }
}
