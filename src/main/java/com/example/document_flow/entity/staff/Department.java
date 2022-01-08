package com.example.document_flow.entity.staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.Set;

/**
 * Подразделение
 *
 * @author Баратов Руслан
 */
@XmlRootElement
public class Department extends Staff implements Cloneable{

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

    /**
     * Организация
     */
    private Organization organization;

    public Department() {
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

    public Organization getOrganization() {
        return organization;
    }

    @XmlElement
    public void setOrganization(Organization organization) {
        this.organization = organization;
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Department().newBuilder()
                .setId(this.getId())
                .setShortName(this.shortName)
                .setFullName(this.fullName)
                .setContactPhoneNumber(this.contactPhoneNumber)
                .setManager(new Person().newBuilder()
                        .setId(this.getManager().getId())
                        .setName(this.getManager().getName())
                        .setSurname(this.getManager().getSurname())
                        .setPatronymic(this.getManager().getPatronymic())
                        .setPhoneNumber(this.getManager().getPhoneNumber())
                        .setDateOfBirth(this.getManager().getDateOfBirth())
                        .setPost(this.getManager().getPost())
                        .build())
                .build();
    }

    public Builder newBuilder() {
        return new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(long id) {
            Department.this.setId(id);
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

        public Builder setContactPhoneNumber(String contactPhoneNumber) {
            Department.this.contactPhoneNumber = contactPhoneNumber;
            return this;
        }

        public Builder SetOrganization(Organization organization) {
            Department.this.organization = organization;
            return this;
        }

        public Department build() {
            return Department.this;
        }
    }
}