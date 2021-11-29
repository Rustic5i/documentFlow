package com.example.document_flow.factory.generator.entity;

/**
 * Объект хранит в себе различные типы наименований "организационных структур"
 *
 * @author Баратов Руслан
 */
public class NameStaff {

    /**
     * Полное наименование
     */
    private String fullName;

    /**
     * Краткое наименование
     */
    private String shortName;

    public NameStaff() {
    }

    public NameStaff(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }
}
