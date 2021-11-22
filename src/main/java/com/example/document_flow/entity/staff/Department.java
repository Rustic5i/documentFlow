package com.example.document_flow.entity.staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Подразделение
 */
@XmlRootElement
public class Department extends Staff{

    /**
     * Полное наименование
     */
    private String fullName;

    /**
     * Краткое наименовани
     */
    private String shortName;

    /**
     * Руководитель
     */
    private Person manager;

    /**
     * Контактные телефоны
     */
    private int contactPhoneNumber;

    public String getFullName() {
        return fullName;
    }

    @XmlElement
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    @XmlElement
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Person getManager() {
        return manager;
    }

    @XmlElement
    public void setManager(Person manager) {
        this.manager = manager;
    }

    public int getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    @XmlElement
    public void setContactPhoneNumber(int contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }
}