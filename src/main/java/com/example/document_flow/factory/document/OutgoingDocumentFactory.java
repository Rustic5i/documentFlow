package com.example.document_flow.factory.document;

import com.example.document_flow.entity.document.Outgoing;

/**
 * Класс-фабрика для генерации Входящего документа (Outgoing)
 *
 * @author Баратов Руслан
 */
public class OutgoingDocumentFactory extends AbstractDocumentFactory<Outgoing> {

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
     * @return Тип создаваемого документа
     */
    @Override
    public Class getTypeObject() {
        return Outgoing.class;
    }

}
