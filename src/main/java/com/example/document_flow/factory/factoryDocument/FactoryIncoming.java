package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.entity.Incoming;

/**
 * Класс-фабрика для генерации Входящего документа (Incoming)
 *
 * @author Баратов Руслан
 */
public class FactoryIncoming extends AbstractFactory<Incoming> {

    /**
     * Фабричный метод для создания объекта класса <code>Incoming</code>
     * @return новый инстанс класса <code>Incoming</code>
     */
    @Override
    public Incoming createInstance() {
        return new Incoming();
    }

    /**
     * Поля объекта заполняются случайными значениями
     * @param incoming входящий документ
     */
    @Override
    public void fillAdditionalFields(Incoming incoming) {
        incoming.setSource(getDataGenerator().getSource());
        incoming.setAddressee(getDataGenerator().getAddressee());
        incoming.setOutgoingNumber(getDataGenerator().getOutgoingNumber());
        incoming.setOutgoingRegistrationDate(getDataGenerator().getOutgoingRegistrationDate());
    }

    /**
     * @return Тип создваемого документа
     */
    @Override
    public Class getDocumentType() {
        return Incoming.class;
    }
}
