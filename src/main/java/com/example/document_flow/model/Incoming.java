package com.example.document_flow.model;

import com.example.document_flow.model.person.Person;

import java.text.MessageFormat;
import java.util.Date;

public class Incoming extends Document {

    private Person source; // отправитель

    private String addressee; //addressee

    private Long outgoingNumber; // исходящий номер

    private Date outgoingRegistrationDate; //исходящая дата регистрации

    @Override
    public String toString() {
        return MessageFormat
                .format("Входящее №{0} от {1} Имя документа : {2}",
                        super.getRegistrationNumber(), super.getDateRegistration().getCalendar().getTime(), super.getName());
    }

    public Person getSource() {
        return source;
    }

    public void setSource(Person source) {
        this.source = source;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public Long getOutgoingNumber() {
        return outgoingNumber;
    }

    public void setOutgoingNumber(Long outgoingNumber) {
        this.outgoingNumber = outgoingNumber;
    }

    public Date getOutgoingRegistrationDate() {
        return outgoingRegistrationDate;
    }

    public void setOutgoingRegistrationDate(Date outgoingRegistrationDate) {
        this.outgoingRegistrationDate = outgoingRegistrationDate;
    }
}
