package com.example.document_flow.factory;

import com.example.document_flow.factory.abstr.IDocumentFactory;
import com.example.document_flow.factory.generate.GenerateIncoming;
import com.example.document_flow.factory.generate.GenerateOutgoing;
import com.example.document_flow.factory.generate.GenerateTask;
import com.example.document_flow.model.Document;
import com.example.document_flow.model.Incoming;
import com.example.document_flow.model.Outgoing;
import com.example.document_flow.model.Task;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentFactory implements IDocumentFactory {

    private List<Document> documentList = new ArrayList<>();

    @Override
    public Document getDocument(Class<? extends Document> doc) {

        if (Task.class.isAssignableFrom(doc)){
            return GenerateTask.getRandomInstance();
        }
        else if (Outgoing.class.isAssignableFrom(doc)){
            return GenerateOutgoing.getRandomInstance();
        }
        else if (Incoming.class.isAssignableFrom(doc)){
            return GenerateIncoming.getRandomInstance();
        }
        else {
            System.out.println("Получил Document");
        }
        return null;
    }
}
