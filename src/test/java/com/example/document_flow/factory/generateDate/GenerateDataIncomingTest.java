package com.example.document_flow.factory.generateDate;

import com.example.document_flow.model.person.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GenerateDataIncomingTest {

    private GenerateDataIncoming generateDataIncoming = GenerateDataIncoming.getInstance();

    @Test
    void generateOutgoingRegistrationDate() {
        Date registrationDate = generateDataIncoming.generateOutgoingRegistrationDate();

        assertNotNull(registrationDate);
    }

    @Test
    void getSource() {
        Person source = generateDataIncoming.getSource();

        assertNotNull(source);
        assertNotNull(source.getName());
    }

    @Test
    void getAddressee() {
        Object addressee = generateDataIncoming.getAddressee();

        assertNotNull(addressee);
        assertInstanceOf(String.class,addressee);
    }

    @Test
    void getOutgoingNumber() {
        Long outgoingNumber = generateDataIncoming.getOutgoingNumber();

        assertNotNull(outgoingNumber);
    }

    @DisplayName("GenerateDataIncoming должен быть синглтон")
    @Test
    void getInstance() {
        GenerateDataIncoming actual = GenerateDataIncoming.getInstance();

        assertNotNull(actual);
        assertSame(generateDataIncoming,actual);
    }
}