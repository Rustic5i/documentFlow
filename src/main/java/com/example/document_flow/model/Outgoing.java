package com.example.document_flow.model;

import lombok.Getter;
import lombok.Setter;
import java.text.MessageFormat;

@Getter
@Setter
public class Outgoing extends Document{

    private String addressee;       //адресат

    private String deliveryMethod; //способ доставки

    public Outgoing() {
        super();
    }


    @Override
    public String toString() {
        return MessageFormat
                .format("Исходящий №{0} от {1} Имя документа : {2}",
                        super.getRegistrationNumber(), super.getDateRegistration(), super.getName());
    }
}
