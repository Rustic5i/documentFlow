package com.example.document_flow.entity.document;

import com.example.document_flow.entity.staff.Person;

import java.text.MessageFormat;
import java.util.Date;

/**
 * Поручение
 * @author Баратов Руслан
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
    private Person orderController;

    @Override
    public String toString() {
        return MessageFormat
                .format("Поручение {0}", super.toString());
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

    public Person getOrderController() {
        return orderController;
    }

    public void setOrderController(Person orderController) {
        this.orderController = orderController;
    }
}
