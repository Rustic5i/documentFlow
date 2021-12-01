package com.example.document_flow.entity.document;

import com.example.document_flow.entity.staff.Person;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.MessageFormat;
import java.util.Date;

/**
 * Поручение
 *
 * @author Баратов Руслан
 */
@XmlRootElement
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

    @XmlElement
    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Date getTermOfExecution() {
        return termOfExecution;
    }

    @XmlElement
    public void setTermOfExecution(Date termOfExecution) {
        this.termOfExecution = termOfExecution;
    }

    public Person getResponsibleExecutor() {
        return responsibleExecutor;
    }

    @XmlElement
    public void setResponsibleExecutor(Person responsibleExecutor) {
        this.responsibleExecutor = responsibleExecutor;
    }

    public String getSignOfControl() {
        return signOfControl;
    }

    @XmlElement
    public void setSignOfControl(String signOfControl) {
        this.signOfControl = signOfControl;
    }

    public Person getOrderController() {
        return orderController;
    }

    @XmlElement
    public void setOrderController(Person orderController) {
        this.orderController = orderController;
    }

    public Builder newBuilder() {
        return new Builder();
    }

    public class Builder {

        private Builder() {
        }


        public Builder setDateOfIssue(Date dateOfIssue) {
            Task.this.dateOfIssue = dateOfIssue;
            return this;
        }

        public Builder setTermOfExecution(Date termOfExecution) {
            Task.this.termOfExecution = termOfExecution;
            return this;
        }

        public Builder setResponsibleExecutor(Person responsibleExecutor) {
            Task.this.responsibleExecutor = responsibleExecutor;
            return this;
        }

        public Builder setSignOfControl(String signOfControl) {
            Task.this.signOfControl = signOfControl;
            return this;
        }

        public Builder setOrderController(Person orderController) {
            Task.this.orderController = orderController;
            return this;
        }

        public Task build() {
            return Task.this;
        }

        public Builder setId(Long id) {
            Task.this.setId(id);
            return this;
        }

        public Builder setName(String name) {
            Task.this.setName(name);
            return this;
        }

        public Builder setText(String text) {
            Task.this.setText(text);
            return this;
        }

        public Builder setRegistrationNumber(Long registrationNumber) {
            Task.this.setRegistrationNumber(registrationNumber);
            return this;
        }

        public Builder setDateRegistration(Date dateRegistration) {
            Task.this.setDateRegistration(dateRegistration);
            return this;
        }

        public Builder setAuthor(Person author) {
            Task.this.setAuthor(author);
            return this;
        }
    }
}
