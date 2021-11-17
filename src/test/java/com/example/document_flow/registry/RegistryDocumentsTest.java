package com.example.document_flow.registry;

import com.example.document_flow.entity.Document;
import com.example.document_flow.entity.Incoming;
import com.example.document_flow.entity.person.Person;
import com.example.document_flow.myException.DocumentExistsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegistryDocumentsTest {

    private RegistryDocuments registry = RegistryDocuments.getInstance();

    private List<Document> documentList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 3; i++) {
            Incoming incoming = new Incoming();
            incoming.setAuthor(new Person("Руслан"+i));
            incoming.setRegistrationNumber((long) 1 + i);
            incoming.setDateRegistration(new GregorianCalendar(2018, 5, 11).getTime());
            incoming.setName("Входящий документ" + i);
            documentList.add(incoming);
        }
    }

    @Test
    void saveDocument() throws DocumentExistsException {
        Incoming expected = new Incoming();
        expected.setAuthor(new Person("Руслан"));
        expected.setRegistrationNumber((long) 99);
        expected.setDateRegistration(new GregorianCalendar(2018, 5, 11).getTime());
        expected.setName("Входящий документ");

        registry.saveDocument(expected);
        Document actual = registry.getDocumentByRegNumber(expected.getRegistrationNumber());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void getAllDocument() throws DocumentExistsException {
        registry.saveDocument(documentList);
        List<Document> actual = registry.getAllDocument();
        assertNotNull(actual);
        assertEquals(documentList,actual);
    }

    @DisplayName("В случае, если документ с регистрационным номером уже существует, то необходимо выбрасить исключение DocumentExistsException")
    @Test
    void trowsDocumentExistsException() throws DocumentExistsException {
        Incoming expected = new Incoming();
        expected.setAuthor(new Person("Руслан"));
        expected.setRegistrationNumber((long) 88);
        expected.setDateRegistration(new GregorianCalendar(2018, 5, 11).getTime());
        expected.setName("Входящий документ");

        registry.saveDocument(expected);

        assertThrows(DocumentExistsException.class,()->registry.saveDocument(expected));
    }

    @DisplayName("RegistryDocuments должен быть синглтон")
    @Test
    void getInstance() {
        RegistryDocuments actual = RegistryDocuments.getInstance();

        assertNotNull(actual);
        assertSame(registry, actual);
    }

    @AfterEach
    void afterAll() {
        registry = null;
    }
}