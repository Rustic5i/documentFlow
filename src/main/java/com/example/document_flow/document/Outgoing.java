package com.example.document_flow.document;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Исходящий документ
 */
public class Outgoing extends Document {

    /**
     * Адресат
     */
    private String addressee;

    /**
     * Способ доставки
     */
    private String deliveryMethod;

    @Override
    public String toString() {
        return MessageFormat
                .format("Исходящий №{0} от {1} Имя документа : {2}",
                        super.getRegistrationNumber(), new SimpleDateFormat("d MMMM yyyy")
                                .format(super.getDateRegistration()),
                        super.getName());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Outgoing outgoing = (Outgoing) o;
        return Objects.equals(addressee, outgoing.addressee) && Objects.equals(deliveryMethod, outgoing.deliveryMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), addressee, deliveryMethod);
    }
}
