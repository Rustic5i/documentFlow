package com.example.document_flow.factory.factoriesEnum;

import com.example.document_flow.factory.abstr.Factory;
import com.example.document_flow.factory.factoryDocument.FactoryIncoming;
import com.example.document_flow.factory.factoryDocument.FactoryOutgoing;
import com.example.document_flow.factory.factoryDocument.FactoryTask;

/**
 * - это перечисление класс-фабрик имплементирующих интерфейс <code>Factory</code>
 */
public enum FactoriesEnum {

    /**
     * Предоставляет класс-фабрику <code>FactoryIncoming</code>
     */
    INCOMING {
        @Override
        public Factory getFactoryDocument() {
            return new FactoryIncoming();
        }
    },
    /**
     * Предоставляет класс-фабрику <code>FactoryOutgoing</code>
     */
    OUTGOING {
        @Override
        public Factory getFactoryDocument() {
            return new FactoryOutgoing();
        }
    },
    /**
     * Предоставляет класс-фабрику <code>FactoryTask</code>
     */
    TASK {
        @Override
        public Factory getFactoryDocument() {
            return new FactoryTask();
        }
    };

    public abstract Factory getFactoryDocument();
}
