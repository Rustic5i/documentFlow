package com.example.document_flow.entity.document;

import com.example.document_flow.entity.staff.Person;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

/**
 * Документ
 *
 * @author Баратов Руслан
 */
public class Document implements Comparable<Document> {

    /**
     * Идентификатор документа
     */
    private Long id;

    /**
     * Название документа
     */
    private String name;

    /**
     * Текст документа
     */
    private String text;

    /**
     * Регистрационный номер документа
     */
    private Long registrationNumber;

    /**
     * Дата регистрации документа
     */
    private Date dateRegistration;

    /**
     * Автор документа
     */
    private Person author;

    @Override
    public int compareTo(Document o) {
        return Comparator.comparing(Document::getAuthor)
                .thenComparing(Document::getDateRegistration)
                .compare(this, o);
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

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return MessageFormat
                .format("№{0} от {1} Имя документа : {2}",
                        getRegistrationNumber(),
                        new SimpleDateFormat("d MMMM yyyy")
                                .format(getDateRegistration()),
                        getName());
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
