package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.entity.Document;
import com.example.document_flow.entity.Outgoing;
import com.example.document_flow.factory.abstr.Factory;

/**
 * <code>FactoryOutgoing</code> класс-фабрика для генерации Входящего документа (Outgoing)
 *
 * @author Баратов Руслан
 */
public class FactoryOutgoing extends MapperDocument implements Factory {

    /**
     * Фабричный метод для создания объекта класса <code>Outgoing</code>
     * поля объекта заполняются случайными значениями
     *
     * @return document с заполненными, рандомными значениями
     */
    @Override
    public Document createDocument() {
        Outgoing outgoing = new Outgoing();
        fillTheBasicData(outgoing);
        outgoing.setAddressee(getDataGenerator().getAddressee());
        outgoing.setDeliveryMethod(getDataGenerator().getDeliveryMethod());
        return outgoing;
    }

}
