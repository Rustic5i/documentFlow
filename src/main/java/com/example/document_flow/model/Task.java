package com.example.document_flow.model;

import com.example.document_flow.model.person.Person;

import java.text.MessageFormat;
import java.util.Date;

public class Task extends Document {

    private Date dateOfIssue;

    private Date termOfExecution;//срок исполнения поручения

    private Person responsibleExecutor; //ответственный исполнитель

    private String signOfControl;  //признак контрольности

    private String orderController; //контролер поручения

    @Override
    public String toString() {
        return MessageFormat
                .format("Поручение №{0} от {1} Имя документа : {2}",
                        super.getRegistrationNumber(), super.getDateRegistration().getCalendar().getTime(), super.getName());
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Date getTermOfExecution() {
        return termOfExecution;
    }

    public void setTermOfExecution(Date termOfExecution) {
        this.termOfExecution = termOfExecution;
    }

    public Person getResponsibleExecutor() {
        return responsibleExecutor;
    }

    public void setResponsibleExecutor(Person responsibleExecutor) {
        this.responsibleExecutor = responsibleExecutor;
    }

    public String getSignOfControl() {
        return signOfControl;
    }

    public void setSignOfControl(String signOfControl) {
        this.signOfControl = signOfControl;
    }

    public String getOrderController() {
        return orderController;
    }

    public void setOrderController(String orderController) {
        this.orderController = orderController;
    }
}
