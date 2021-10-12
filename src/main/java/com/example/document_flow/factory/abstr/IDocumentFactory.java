package com.example.document_flow.factory.abstr;

import com.example.document_flow.model.Document;

public interface IDocumentFactory {

    Document getDocument(Class<? extends Document> doc);
}
