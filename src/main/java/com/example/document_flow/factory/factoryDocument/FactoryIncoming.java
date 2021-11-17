package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.entity.Document;
import com.example.document_flow.entity.Incoming;
import com.example.document_flow.factory.abstr.Factory;

/**
 * <code>FactoryIncoming</code> класс-фабрика для генерации Входящего документа (Incoming)
 * @author Баратов Руслан
 */
public class FactoryIncoming extends MapperDocument implements Factory {

    /**
     * Фабричный метод для создания объекта класса <code>Incoming</code>
     * поля объекта заполняются случайными значениями
     *
     * @return document с заполненными, рандомными значениями
     */
    @Override
    public Document createDocument() {
        Incoming incoming = new Incoming();
        fillTheBasicData(incoming);
        incoming.setSource(getDataGenerator().getSource());
        incoming.setAddressee(getDataGenerator().getAddressee());
        incoming.setOutgoingNumber(getDataGenerator().getOutgoingNumber());
        incoming.setOutgoingRegistrationDate(getDataGenerator().getOutgoingRegistrationDate());
        return incoming;
    }
}
