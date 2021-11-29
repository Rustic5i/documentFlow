package com.example.document_flow.entity.document;

import com.example.document_flow.entity.staff.Person;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Исходящий документ
 * @author Баратов Руслан
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
                .format("Исходящий {0}", super.toString());
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


    public Builder newBuilder() {
        return new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Outgoing build() {
            return Outgoing.this;
        }

        public Builder setAddressee(String addressee) {
            Outgoing.this.addressee = addressee;
            return this;
        }

        public Builder setDeliveryMethod(String deliveryMethod) {
            Outgoing.this.deliveryMethod = deliveryMethod;
            return this;
        }

        public Builder setId(Long id) {
            Outgoing.this.setId(id);
            return this;
        }

        public Builder setName(String name) {
            Outgoing.this.setName(name);
            return this;
        }

        public Builder setText(String text) {
            Outgoing.this.setText(text);
            return this;
        }

        public Builder setRegistrationNumber(Long registrationNumber) {
            Outgoing.this.setRegistrationNumber(registrationNumber);
            return this;
        }

        public Builder setDateRegistration(Date dateRegistration) {
            Outgoing.this.setDateRegistration(dateRegistration);
            return this;
        }

        public Builder setAuthor(Person author) {
            Outgoing.this.setAuthor(author);
            return this;
        }
    }
}
