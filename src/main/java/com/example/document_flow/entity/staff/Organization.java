package com.example.document_flow.entity.staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Организация
 *
 * @author Баратов Руслан
 */
@XmlRootElement
public class Organization extends Staff implements Cloneable {

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

    public Organization() {
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
        Organization that = (Organization) o;
        return Objects.equals(fullName, that.fullName) && Objects.equals(shortName, that.shortName) && Objects.equals(manager, that.manager) && Objects.equals(contactPhoneNumber, that.contactPhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, shortName, manager, contactPhoneNumber);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Organization().newBuilder()
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

    public Organization.Builder newBuilder() {
        return new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setFullName(String fullName) {
            Organization.this.fullName = fullName;
            return this;
        }

        public Builder setId(long id) {
            Organization.this.setId(id);
            return this;
        }

        public Builder setShortName(String shortName) {
            Organization.this.shortName = shortName;
            return this;
        }

        public Builder setManager(Person manager) {
            Organization.this.manager = manager;
            return this;
        }

        public Builder setContactPhoneNumber(String contactPhoneNumber) {
            Organization.this.contactPhoneNumber = contactPhoneNumber;
            return this;
        }

        public Organization build() {
            return Organization.this;
        }
    }
}
