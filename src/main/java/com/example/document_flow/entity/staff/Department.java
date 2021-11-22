package com.example.document_flow.entity.staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Department extends Staff{

    private String fullName;

    private String shortName;

    private Person manager;

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