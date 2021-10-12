package com.example.document_flow.model;

import com.example.document_flow.model.person.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Task extends Document{

    private Date dateOfIssue;

    private Date termOfExecution;//срок исполнения поручения

    private Person responsibleExecutor; //ответственный исполнитель

    private String signOfControl;  //признак контрольности

    private String orderController; //контролер поручения

    public Task() {
        super();
    }

    @Override
    public String toString() {
        return "Поручение №"+super.getRegistrationNumber()+" " +
                "от "+super.getDateRegistration()+". "+"" +
                "Имя документа :"+super.getName();
    }
}
