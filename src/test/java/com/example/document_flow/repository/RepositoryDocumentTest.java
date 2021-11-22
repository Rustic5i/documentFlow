package com.example.document_flow.repository;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.entity.document.Incoming;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.myException.DocumentExistsException;
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

public class RepositoryDocumentTest {

    private RepositoryDocument registry = RepositoryDocument.getInstance();

    private List<Document> documentList = new ArrayList<>();

    private final Person person = new Person();

    @BeforeEach
    public void setUp() {

        person.setId(2);
        person.setName("Андрей");
        person.setSurname("Сабиров");
        person.setPatronymic("Васильевич");
        person.setPost("Бугалтер");
        person.setDateOfBirth(new GregorianCalendar(1998, 12, 12).getTime());
        person.setPhoneNumber(866555445);

        for (int i = 0; i < 3; i++) {
            Incoming incoming = new Incoming();
            incoming.setAuthor(person);
            incoming.setRegistrationNumber((long) 1 + i);
            incoming.setDateRegistration(new GregorianCalendar(2018, 5, 11).getTime());
            incoming.setName("Входящий документ" + i);
            documentList.add(incoming);
        }
    }

    @Test
    public void saveDocument() throws DocumentExistsException {
        Incoming expected = new Incoming();
        expected.setAuthor(person);
        expected.setRegistrationNumber((long) 99);
        expected.setDateRegistration(new GregorianCalendar(2018, 5, 11).getTime());
        expected.setName("Входящий документ");

        registry.saveDocument(expected);
        Document actual = registry.getDocumentByRegNumber(expected.getRegistrationNumber());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void getAllDocument() {
        registry.saveDocument(documentList);
        List<Document> actual = registry.getAllDocument();
        assertNotNull(actual);
        assertEquals(documentList, actual);
    }

    @DisplayName("В случае, если документ с регистрационным номером уже существует, то необходимо выбрасить исключение DocumentExistsException")
    @Test
    public void trowsDocumentExistsException() throws DocumentExistsException {
        Incoming expected = new Incoming();
        expected.setAuthor(person);
        expected.setRegistrationNumber((long) 88);
        expected.setDateRegistration(new GregorianCalendar(2018, 5, 11).getTime());
        expected.setName("Входящий документ");

        registry.saveDocument(expected);

        assertThrows(DocumentExistsException.class, () -> registry.saveDocument(expected));
    }

    @DisplayName("RepositoryDocument должен быть синглтон")
    @Test
    public void getInstance() {
        RepositoryDocument actual = RepositoryDocument.getInstance();

        assertNotNull(actual);
        assertSame(registry, actual);
    }

}