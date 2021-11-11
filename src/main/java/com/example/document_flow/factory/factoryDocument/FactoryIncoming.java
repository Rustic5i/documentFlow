package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.abstr.Factory;
import com.example.document_flow.document.Document;
import com.example.document_flow.document.Incoming;
import com.example.document_flow.myException.DocumentExistsException;

/**
 * <code>FactoryIncoming</code> класс-фабрика для генерации Входящего документа (Incoming)
 */
public class FactoryIncoming extends FactoryDocument implements Factory {

    /**
     * Фабричный метод для создания объекта класса <code>Incoming</code>
     * поля объекта заполняются случайными значениями
     *
     * @return document с заполненными, рандомными значениями
     * @throws DocumentExistsException если документ с генерируемым регистрационным номером уже существует
     */
    @Override
    public Document creatDocument() throws DocumentExistsException {
        Incoming incoming = new Incoming();
        incoming = (Incoming) getRandomInstance(incoming);
        incoming.setSource(super.getDataGenerator().getSource());
        incoming.setAddressee(super.getDataGenerator().getAddressee());
        incoming.setOutgoingNumber(super.getDataGenerator().getOutgoingNumber());
        incoming.setOutgoingRegistrationDate(super.getDataGenerator().getOutgoingRegistrationDate());
        return incoming;
    }
}
