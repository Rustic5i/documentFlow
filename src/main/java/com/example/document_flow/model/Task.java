package com.example.document_flow.model;

import com.example.document_flow.model.person.Person;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Поручение
 */
public class Task extends Document {

    /**
     * Дата выдачи поручения
     */
    private Date dateOfIssue;

    /**
     * Срок исполнения поручения
     */
    private Date termOfExecution;

    /**
     * Ответственный исполнитель
     */
    private Person responsibleExecutor;

    /**
     * Признак контрольности
     */
    private String signOfControl;

    /**
     * Контролер поручения
     */
    private String orderController;

    @Override
    public String toString() {
        return MessageFormat
                .format("Поручение №{0} от {1} Имя документа : {2}",
                        super.getRegistrationNumber(),
                        new SimpleDateFormat("d MMMM yyyy")
                                .format(super.getDateRegistration()),
                        super.getName());
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
