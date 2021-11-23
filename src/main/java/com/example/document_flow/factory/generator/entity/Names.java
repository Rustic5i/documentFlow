package com.example.document_flow.factory.generator.entity;

/**
 * Обьект хранит в себе различные типы наименований "организационных структур"
 */
public class Names {

    /**
     * Полное наименование
     */
    private String fullName;

    /**
     * Краткое наименование
     */
    private String shortName;

    public Names() {
    }

    public Names(String fullName, String shortName) {
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
