package com.example.document_flow.factory.factoriesEnum;

import com.example.document_flow.factory.abstr.Factory;
import com.example.document_flow.factory.factoryDocument.FactoryIncoming;
import com.example.document_flow.factory.factoryDocument.FactoryOutgoing;
import com.example.document_flow.factory.factoryDocument.FactoryTask;

public enum FactoriesEnum {

    INCOMING {
        @Override
        public Factory getFactoryDocument() {
            return new FactoryIncoming();
        }
    },
    OUTGOING {
        @Override
        public Factory getFactoryDocument() {
            return new FactoryOutgoing();
        }
    },
    TASK {
        @Override
        public Factory getFactoryDocument() {
            return new FactoryTask();
        }
    };

    public abstract Factory getFactoryDocument();
}
