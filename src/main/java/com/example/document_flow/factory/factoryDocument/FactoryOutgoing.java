package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.abstr.Factory;
import com.example.document_flow.factory.generateDate.GenerateDataOutgoing;
import com.example.document_flow.model.Document;
import com.example.document_flow.model.Outgoing;
import com.example.document_flow.myException.DocumentExistsException;

/**
 * <code>FactoryOutgoing</code> класс-фабрика для генерации Входящего документа (Outgoing)
 */
public class FactoryOutgoing extends FactoryDocument implements Factory {

    private GenerateDataOutgoing generateDataOutgoing;

    @Override
    public Document creatDocument() throws DocumentExistsException {
        this.generateDataOutgoing = makeGenerateDataIncoming();
        Outgoing outgoing = new Outgoing();
        outgoing = (Outgoing) getRandomInstance(outgoing);
        outgoing.setAddressee(generateDataOutgoing.getAddressee());
        outgoing.setDeliveryMethod(generateDataOutgoing.getDeliveryMethod());
        return outgoing;
    }

    @Override
    public GenerateDataOutgoing makeGenerateDataIncoming() {
        return GenerateDataOutgoing.getInstance();
    }
}
