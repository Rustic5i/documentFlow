package com.example.document_flow.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        return "Исходящий №"+super.getRegistrationNumber()+" " +
                "от "+super.getDateRegistration()+". "+"" +
                "Имя документа :"+super.getName();
    }
}
