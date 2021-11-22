package com.example.document_flow.staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Objects;

@XmlRootElement
public class Person extends Staff{

    private String surname;

    private String name;

    private String patronymic;

    private String post;

    private Date dateOfBirth;

    private int phoneNumber;

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

    @Override
    public String toString() {
        return MessageFormat.format("{0} {1} {2}",surname,name,patronymic);
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
}
