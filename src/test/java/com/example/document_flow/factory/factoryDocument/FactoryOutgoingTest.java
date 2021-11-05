package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.model.Outgoing;
import com.example.document_flow.myException.DocumentExistsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class FactoryOutgoingTest {

    private FactoryOutgoing factoryOutgoing;

    private Outgoing outgoing;

    @BeforeEach
    void setUp() {
        factoryOutgoing = new FactoryOutgoing();
    }

    @Test
    void creatDocument() {

    }

    @Test
    void getUniqueOutgoing() throws DocumentExistsException {
        Set<Outgoing> setRegistrationNumber = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            outgoing = (Outgoing) factoryOutgoing.creatDocument();
            if (setRegistrationNumber.contains(outgoing)) {
                Assertions.fail();
            }else {
                setRegistrationNumber.add(outgoing);
            }
        }
    }

    @AfterEach
    void tearDown() {
    }
}