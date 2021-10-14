package com.example.document_flow.model;

import com.example.document_flow.model.abstr.Storable;
import com.example.document_flow.model.person.Person;
import lombok.Getter;
import lombok.Setter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Getter
@Setter
public abstract class Document implements Comparable<Document>, Storable {

    private Long id;

    private String name;

    private String text;

    private Long registrationNumber; //регистрационный номер документа

    private SimpleDateFormat dateRegistration = new SimpleDateFormat("EEEE, d MMMM yyyy");

    private Person author;

    private Calendar calendar;


    public Document() {
        calendar = new GregorianCalendar();
    }

    public String getDateRegistration() {
        Calendar calendar = new GregorianCalendar();
        return dateRegistration.format(calendar.getTime());
    }

    @Override
    public int compareTo(Document o) {
        return this.calendar.getTime().compareTo(o.calendar.getTime());
    }

    @Override
    public String toString() {
        return "Документ: " +
                " author='" + author + "\n" +
                " name='" + name + "\n" +
                ", text='" + text + "\n" +
                ", registrationNumber=" + registrationNumber +"\n"+
                ", dateRegistration=" + getDateRegistration() +"\n";
    }

    @Override
    public Long getIdDocument() {
        return null;
    }

    @Override
    public String getNameTable() {
        return null;
    }
}
