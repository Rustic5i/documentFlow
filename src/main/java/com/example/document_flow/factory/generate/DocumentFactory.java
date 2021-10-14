package com.example.document_flow.factory.generate;

import com.example.document_flow.model.Document;
import com.example.document_flow.model.DocumentTypes;

public class DocumentFactory {

    public Document getDocument(DocumentTypes doc) {
            return switch (doc) {
                case TASK -> GenerateTask.getRandomInstance();
                case OUTGOING -> GenerateOutgoing.getRandomInstance();
                case INCOMING -> GenerateIncoming.getRandomInstance();
            };
    }
}
