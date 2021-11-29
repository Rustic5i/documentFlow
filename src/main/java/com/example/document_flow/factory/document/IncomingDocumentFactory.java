package com.example.document_flow.factory.document;

import com.example.document_flow.entity.document.Incoming;

/**
 * Класс-фабрика для генерации Входящего документа (Incoming)
 *
 * @author Баратов Руслан
 */
public class IncomingDocumentFactory extends AbstractDocumentFactory<Incoming> {

    /**
     * Фабричный метод для создания объекта класса <code>Incoming</code>
     *
     * @return новый инстанс класса <code>Incoming</code>
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
                .setSource(getDATA_GENERATOR().getSource())
                .setAddressee(getDATA_GENERATOR().getAddressee())
                .setOutgoingNumber(getDATA_GENERATOR().getOutgoingNumber())
                .setOutgoingRegistrationDate(getDATA_GENERATOR().getOutgoingRegistrationDate())
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
