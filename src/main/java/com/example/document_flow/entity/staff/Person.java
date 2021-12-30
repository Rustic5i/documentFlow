package com.example.document_flow.entity.staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

/**
 * Сотрудник
 *
 * @author Баратов Руслан
 */
@XmlRootElement
public class Person extends Staff implements Comparable<Person> {

    /**
     * Фамилия
     */
    private String surname;

    /**
     * Имя
     */
    private String name;

    /**
     * Отчество
     */
    private String patronymic;

    /**
     * Должность
     */
    private String post;

    /**
     * Дата рождения
     */
    private Date dateOfBirth;

    /**
     * Номер телефона
     */
    private int phoneNumber;

    private Department department;

    public Person() {
    }

    public String getSurname() {
        return surname;
    }

    @XmlElement
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    @XmlElement
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPost() {
        return post;
    }

    @XmlElement
    public void setPost(String post) {
        this.post = post;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @XmlElement
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    @XmlElement
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Department getDepartment() {
        return department;
    }

    @XmlElement
    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} {1} {2}", surname, name, patronymic);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return phoneNumber == person.phoneNumber && Objects.equals(surname, person.surname) && Objects.equals(name, person.name) && Objects.equals(patronymic, person.patronymic) && Objects.equals(post, person.post) && Objects.equals(dateOfBirth, person.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, patronymic, post, dateOfBirth, phoneNumber);
    }

    @Override
    public int compareTo(Person o) {
        return Comparator.comparing(Person::getSurname)
                .thenComparing(Person::getName)
                .thenComparing(Person::getPatronymic)
                .compare(this, o);
    }

    public Builder newBuilder() {
        return new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setSurname(String surname) {
            Person.this.surname = surname;
            return this;
        }

        public Builder setName(String name) {
            Person.this.name = name;
            return this;
        }

        public Builder setPatronymic(String patronymic) {
            Person.this.patronymic = patronymic;
            return this;
        }

        public Builder setPost(String post) {
            Person.this.post = post;
            return this;
        }

        public Builder setDateOfBirth(Date dateOfBirth) {
            Person.this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder setPhoneNumber(int phoneNumber) {
            Person.this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setId(long id) {
            Person.this.setId(id);
            return this;
        }

        public Builder setDepartment(Department department) {
            Person.this.department = department;
            return this;
        }

        public Person build() {
            return Person.this;
        }
    }
}
