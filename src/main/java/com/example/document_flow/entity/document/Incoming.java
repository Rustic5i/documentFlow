package com.example.document_flow.entity.document;

import com.example.document_flow.entity.staff.Person;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Входящий документ
 *
 * @author Баратов Руслан
 */
@XmlRootElement
public class Incoming extends Document {

    /**
     * Отправитель
     */
    private Person source;

    /**
     * Адресат
     */
    private String addressee;

    /**
     * Исходящий номер
     */
    private Long outgoingNumber;

    /**
     * Исходящая дата регистрации
     */
    private Date outgoingRegistrationDate;

    @Override
    public String toString() {
        return MessageFormat
                .format("Входящий {0}", super.toString());
    }

    public Incoming() {
    }

    public Person getSource() {
        return source;
    }

    @XmlElement
    public void setSource(Person source) {
        this.source = source;
    }

    public String getAddressee() {
        return addressee;
    }

    @XmlElement
    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public Long getOutgoingNumber() {
        return outgoingNumber;
    }

    @XmlElement
    public void setOutgoingNumber(Long outgoingNumber) {
        this.outgoingNumber = outgoingNumber;
    }

    public Date getOutgoingRegistrationDate() {
        return outgoingRegistrationDate;
    }

    @XmlElement
    public void setOutgoingRegistrationDate(Date outgoingRegistrationDate) {
        this.outgoingRegistrationDate = outgoingRegistrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Incoming incoming = (Incoming) o;
        return Objects.equals(source, incoming.source) && Objects.equals(addressee, incoming.addressee) && Objects.equals(outgoingNumber, incoming.outgoingNumber) && Objects.equals(outgoingRegistrationDate, incoming.outgoingRegistrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), source, addressee, outgoingNumber, outgoingRegistrationDate);
    }

    public Builder newBuilder() {
        return new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Incoming build() {
            return Incoming.this;
        }

        public Builder setSource(Person source) {
            Incoming.this.source = source;
            return this;
        }

        public Builder setAddressee(String addressee) {
            Incoming.this.addressee = addressee;
            return this;
        }

        public Builder setOutgoingNumber(Long outgoingNumber) {
            Incoming.this.outgoingNumber = outgoingNumber;
            return this;
        }

        public Builder setOutgoingRegistrationDate(Date outgoingRegistrationDate) {
            Incoming.this.outgoingRegistrationDate = outgoingRegistrationDate;
            return this;
        }

        public Builder setId(Long id) {
            Incoming.this.setId(id);
            return this;
        }

        public Builder setName(String name) {
            Incoming.this.setName(name);
            return this;
        }

        public Builder setText(String text) {
            Incoming.this.setText(text);
            return this;
        }

        public Builder setRegistrationNumber(Long registrationNumber) {
            Incoming.this.setRegistrationNumber(registrationNumber);
            return this;
        }

        public Builder setDateRegistration(Date dateRegistration) {
            Incoming.this.setDateRegistration(dateRegistration);
            return this;
        }

        public Builder setAuthor(Person author) {
            Incoming.this.setAuthor(author);
            return this;
        }
    }
}
