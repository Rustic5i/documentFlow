package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.abstr.Factory;
import com.example.document_flow.factory.generateDate.GenerateDataIncoming;
import com.example.document_flow.model.Document;
import com.example.document_flow.model.Incoming;
import com.example.document_flow.myException.DocumentExistsException;

//Обязательные теги
//
//Тег @param является «обязательным» (по соглашению) для каждого параметра,
// даже если описание очевидно. Тег @return требуется для каждого метода,
// который возвращает что-то отличное от void, даже если это избыточно с описанием метода.
// (По возможности найдите что-нибудь неизбыточное (в идеале, более конкретное), чтобы использовать его для комментария к тегу.)
//Эти принципы ускоряют автоматизированный поиск и автоматизированную обработку.
// Часто также попытки избежать избыточности окупаются дополнительной ясностью.

/**
 * <code>FactoryIncoming</code> класс-фабрика для генерации Входящего документа (Incoming)
 */
public class FactoryIncoming extends FactoryDocument implements Factory {

    private GenerateDataIncoming generateDataIncoming;

    @Override
    public Document creatDocument() throws DocumentExistsException {
        this.generateDataIncoming = makeGenerateDataIncoming();
        Incoming incoming = new Incoming();
        incoming = (Incoming) getRandomInstance(incoming);
        incoming.setSource(generateDataIncoming.getSource());
        incoming.setAddressee(generateDataIncoming.getAddressee());
        incoming.setOutgoingNumber(generateDataIncoming.getOutgoingNumber());
        incoming.setOutgoingRegistrationDate(generateDataIncoming.generateOutgoingRegistrationDate());
        return incoming;
    }

    @Override
    public GenerateDataIncoming makeGenerateDataIncoming() {
        return GenerateDataIncoming.getInstance();
    }
}
