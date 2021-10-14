package com.example.document_flow.model;

import com.example.document_flow.model.person.Person;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
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
        return MessageFormat
                .format("Поручение №{0} от {1} Имя документа : {2}",
                        super.getRegistrationNumber(), super.getDateRegistration(), super.getName());
    }
}
