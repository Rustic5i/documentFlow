package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.abstr.Factory;
import com.example.document_flow.factory.factoriesEnum.DocumentType;

public class FactoryMain{

    public static Factory creatFactory(DocumentType type) {
        return switch (type){
            case INCOMING -> new FactoryIncoming();
            case OUTGOING -> new FactoryOutgoing();
            case TASK -> new FactoryTask();
        };
    }
}
