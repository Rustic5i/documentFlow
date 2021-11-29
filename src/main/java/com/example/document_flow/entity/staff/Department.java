package com.example.document_flow.entity.staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Подразделение
 *
 * @author Баратов Руслан
 */
@XmlRootElement
public class Department extends Staff {

    /**
     * Полное наименование
     */
    private String fullName;

    /**
     * Краткое наименование
     */
    private String shortName;

    /**
     * Руководитель
     */
    private Person manager;

    /**
     * Контактные телефоны
     */
    private String contactPhoneNumber;

    private Department() {
    }

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

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    @XmlElement
    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(fullName, that.fullName) && Objects.equals(shortName, that.shortName) && Objects.equals(manager, that.manager) && Objects.equals(contactPhoneNumber, that.contactPhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, shortName, manager, contactPhoneNumber);
    }

    public Builder newBuilder() {
        return new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(int id) {
            Builder.this.setId(id);
            return this;
        }

        public Builder setFullName(String fullName) {
            Department.this.fullName = fullName;
            return this;
        }

        public Builder setShortName(String shortName) {
            Department.this.shortName = shortName;
            return this;
        }

        public Builder setManager(Person manager) {
            Department.this.manager = manager;
            return this;
        }

        public Department build() {
            return Department.this;
        }
    }
}