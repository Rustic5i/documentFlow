package com.example.document_flow.model;

import com.example.document_flow.model.person.Person;
import lombok.Getter;
import lombok.Setter;
import java.text.MessageFormat;
import java.util.Date;

@Getter
@Setter
public class Incoming extends Document {

    private Person source; // отправитель

    private String addressee; //addressee

    private Long outgoingNumber; // исходящий номер

    private Date outgoingRegistrationDate; //исходящая дата регистрации

    public Incoming() {
        super();
    }


    @Override
    public String toString() {
        return MessageFormat
                .format("Входящее №{0} от {1} Имя документа : {2}",
                        super.getRegistrationNumber(), super.getDateRegistration(), super.getName());
    }

}
