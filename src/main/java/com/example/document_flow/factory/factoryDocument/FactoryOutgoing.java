package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.abstr.Factory;
import com.example.document_flow.factory.generateDate.GenerateDataOutgoing;
import com.example.document_flow.model.Document;
import com.example.document_flow.model.Outgoing;
import com.example.document_flow.myException.DocumentExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>FactoryOutgoing</code> класс-фабрика для генерации Входящего документа (Outgoing)
 */
public class FactoryOutgoing extends FactoryDocument implements Factory {

    private GenerateDataOutgoing generateDataOutgoing = new GenerateDataOutgoing();

    private static final Logger log = LoggerFactory.getLogger(FactoryOutgoing.class.getName());

    @Override
    public Document creatDocument() {
        Outgoing outgoing = new Outgoing();
        try {
            outgoing = (Outgoing) getRandomInstance(outgoing);
            outgoing.setAddressee(generateDataOutgoing.getAddressee());
            outgoing.setDeliveryMethod(generateDataOutgoing.getDeliveryMethod());
            return outgoing;
        } catch (DocumentExistsException e) {
            log.warn("Exception ", e);
        }
        return null;
    }
}
