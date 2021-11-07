package com.example.document_flow.factory.generateDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerateDataOutgoingTest {

    private GenerateDataOutgoing generateDataOutgoing = GenerateDataOutgoing.getInstance();

    @Test
    void getAddressee() {
        Object addressee = generateDataOutgoing.getAddressee();

        assertNotNull(addressee);
        assertInstanceOf(String.class, addressee);
    }

    @Test
    void getDeliveryMethod() {
        Object deliveryMethod = generateDataOutgoing.getDeliveryMethod();

        assertNotNull(deliveryMethod);
        assertInstanceOf(String.class, deliveryMethod);
    }

    @DisplayName("GenerateDataOutgoing должен быть синглтон")
    @Test
    void getInstance() {
        GenerateDataOutgoing actual = GenerateDataOutgoing.getInstance();

        assertNotNull(actual);
        assertSame(generateDataOutgoing,actual);
    }
}