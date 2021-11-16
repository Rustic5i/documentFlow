package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.abstr.Factory;
import com.example.document_flow.entity.Document;
import com.example.document_flow.entity.Outgoing;
import com.example.document_flow.myException.DocumentExistsException;

/**
 * <code>FactoryOutgoing</code> класс-фабрика для генерации Входящего документа (Outgoing)
 */
public class FactoryOutgoing extends FactoryDocument implements Factory {

    /**
     * Фабричный метод для создания объекта класса <code>Outgoing</code>
     * поля объекта заполняются случайными значениями
     *
     * @return document с заполненными, рандомными значениями
     * @throws DocumentExistsException если документ с генерируемым регистрационным номером уже существует
     */
    @Override
    public Document createDocument() throws DocumentExistsException {
        Outgoing outgoing = new Outgoing();
        outgoing = (Outgoing) fillTheBasicData(outgoing);
        outgoing.setAddressee(super.getDataGenerator().getAddressee());
        outgoing.setDeliveryMethod(super.getDataGenerator().getDeliveryMethod());
        return outgoing;
    }

}
