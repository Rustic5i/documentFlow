package com.example.document_flow.model;

import com.example.document_flow.model.abstr.Storable;
import com.example.document_flow.model.person.Person;
import java.text.SimpleDateFormat;

public abstract class Document implements Comparable<Document>, Storable {

    private Long id;

    private String name;

    private String text;

    private Long registrationNumber; //регистрационный номер документа

    private SimpleDateFormat dateRegistration = new SimpleDateFormat("EEEE, d MMMM yyyy");

    private Person author;

    @Override
    public int compareTo(Document o) {
        return this.getDateRegistration().getCalendar().getTime()
                .compareTo(o.getDateRegistration().getCalendar().getTime());
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

    public void setDateRegistration(SimpleDateFormat dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public SimpleDateFormat getDateRegistration() {
        return dateRegistration;
    }
}
