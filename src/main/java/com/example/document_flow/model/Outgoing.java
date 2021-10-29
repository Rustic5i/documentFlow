package com.example.document_flow.model;

import java.text.MessageFormat;


public class Outgoing extends Document {

    private String addressee;       //адресат

    private String deliveryMethod; //способ доставки

    @Override
    public String toString() {
        return MessageFormat
                .format("Исходящий №{0} от {1} Имя документа : {2}",
                        super.getRegistrationNumber(), super.getDateRegistration().getCalendar().getTime(), super.getName());
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
}
