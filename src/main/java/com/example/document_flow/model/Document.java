package com.example.document_flow.model;

import com.example.document_flow.model.abstr.Storable;
import com.example.document_flow.model.person.Person;
import java.util.Date;
import java.util.Objects;

public abstract class Document implements Comparable<Document>, Storable {

    private Long id;

    private String name;

    private String text;

    private Long registrationNumber; //регистрационный номер документа

    private Date dateRegistration;

    private Person author;

    @Override
    public int compareTo(Document o) {
        return this.getDateRegistration().compareTo(o.dateRegistration);
    }

    @Override
    public Long getIdDocument() {
        return null;
    }

    @Override
    public String getNameTable() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Long registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(id, document.id)
                && Objects.equals(name, document.name)
                && Objects.equals(text, document.text)
                && Objects.equals(registrationNumber, document.registrationNumber)
                && Objects.equals(dateRegistration, document.dateRegistration)
                && Objects.equals(author, document.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, text, registrationNumber, dateRegistration, author);
    }
}
