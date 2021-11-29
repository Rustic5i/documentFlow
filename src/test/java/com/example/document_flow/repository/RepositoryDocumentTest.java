package com.example.document_flow.repository;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.entity.document.Incoming;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.DocumentExistsException;
import com.example.document_flow.repository.document.DocumentRepository;
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

    private DocumentRepository registry = DocumentRepository.getInstance();

    private List<Document> documentList = new ArrayList<>();

    private Person person;

    @BeforeEach
    public void setUp() {

        person = new Person().newBuilder()
                .setId(2)
                .setName("Андрей")
                .setSurname("Сабиров")
                .setPatronymic("Васильевич")
                .setPost("Бугалтер")
                .setDateOfBirth(new GregorianCalendar(1998, 12, 12).getTime())
                .setPhoneNumber(866555445)
                .build();

        for (int i = 0; i < 3; i++) {
            Incoming incoming = new Incoming().newBuilder()
                    .setAuthor(person)
                    .setRegistrationNumber((long)1 + i)
                    .setDateRegistration(new GregorianCalendar(2018, 5, 11).getTime())
                    .setName("Входящий документ"+ i)
                    .build();;

            documentList.add(incoming);
        }
    }

    @Test
    public void saveDocument() throws DocumentExistsException {
        Incoming expected = new Incoming().newBuilder()
                .setAuthor(person)
                .setRegistrationNumber((long)99)
                .setDateRegistration(new GregorianCalendar(2018, 5, 11).getTime())
                .setName("Входящий документ")
                .build();

        registry.save(expected);
        Document actual = registry.getDocumentByRegNumber(expected.getRegistrationNumber());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void getAllDocument() {
        registry.saveAll(documentList);
        List<Document> actual = registry.getAll();
        assertNotNull(actual);
        assertEquals(documentList, actual);
    }

    @DisplayName("В случае, если документ с регистрационным номером уже существует, то необходимо выбрасить исключение DocumentExistsException")
    @Test
    public void trowsDocumentExistsException() throws DocumentExistsException {
        Incoming expected = new Incoming().newBuilder()
                .setAuthor(person)
                .setRegistrationNumber((long)88)
                .setDateRegistration(new GregorianCalendar(2018, 5, 11).getTime())
                .setName("Входящий документ")
                .build();

        registry.save(expected);

        assertThrows(DocumentExistsException.class, () -> registry.save(expected));
    }

    @DisplayName("DocumentRepository должен быть синглтон")
    @Test
    public void getInstance() {
        DocumentRepository actual = DocumentRepository.getInstance();

        assertNotNull(actual);
        assertSame(registry, actual);
    }

}