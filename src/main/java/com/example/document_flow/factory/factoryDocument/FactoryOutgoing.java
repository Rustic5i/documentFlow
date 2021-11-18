package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.entity.Outgoing;

/**
 * <code>FactoryOutgoing</code> класс-фабрика для генерации Входящего документа (Outgoing)
 *
 * @author Баратов Руслан
 */
public class FactoryOutgoing extends AbstractFactory<Outgoing> {

    /**
     * Фабричный метод для создания объекта класса <code>Outgoing</code>
     * @return новый инстанс класса <code>Outgoing</code>
     */
    @Override
    public Outgoing createInstance() {
        return new Outgoing();
    }

    /**
     * Поля объекта заполняются случайными значениями
     * @param outgoing Исходящий документ
     */
    @Override
    public void fillAdditionalFields(Outgoing outgoing) {
        outgoing.setAddressee(getDataGenerator().getAddressee());
        outgoing.setDeliveryMethod(getDataGenerator().getDeliveryMethod());
    }

    /**
     * @return Тип создваемого документа
     */
    @Override
    public Class getDocumentType() {
        return Outgoing.class;
    }

}
