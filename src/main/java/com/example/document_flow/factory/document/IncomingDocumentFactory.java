package com.example.document_flow.factory.document;

import com.example.document_flow.entity.document.Incoming;

/**
 * Класс-фабрика для генерации Входящего документа (IncomingDAO)
 *
 * @author Баратов Руслан
 */
public class IncomingDocumentFactory extends AbstractDocumentFactory<Incoming> {

    /**
     * Фабричный метод для создания объекта класса <code>IncomingDAO</code>
     *
     * @return новый инстанс класса <code>IncomingDAO</code>
     */
    @Override
    public Incoming createInstance() {
        return new Incoming();
    }

    /**
     * Поля объекта заполняются случайными значениями
     *
     * @param incoming входящий документ
     */
    @Override
    public void fillAdditionalFields(Incoming incoming) {
        incoming.newBuilder()
                .setSource(getDataGenerator().getSource())
                .setAddressee(getDataGenerator().getAddressee())
                .setOutgoingNumber(getDataGenerator().getOutgoingNumber())
                .setOutgoingRegistrationDate(getDataGenerator().getOutgoingRegistrationDate())
                .build();
    }

    /**
     * @return Тип создаваемого документа
     */
    @Override
    public Class getTypeObject() {
        return Incoming.class;
    }
}
