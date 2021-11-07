package com.example.document_flow.factory.generateDate;

import com.example.document_flow.model.person.Person;
import com.example.document_flow.myException.DocumentExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GenerateDataDocumentTest {

    @Mock
    private Random randomMock = Mockito.mock(Random.class);

    private GenerateDataDocument generateDataDocument = new GenerateDataDocument();

    public GenerateDataDocumentTest() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("Получаем псевдослучайный регистрационный номер")
    @Test
    void getRegistrationNumber() throws DocumentExistsException, NoSuchFieldException, IllegalAccessException {
        when(randomMock.nextDouble()).thenReturn(0.01);

        Field field = generateDataDocument.getClass().getDeclaredField("random");
        field.setAccessible(true);
        field.set(generateDataDocument, randomMock);

        Object registrationNumber = generateDataDocument.getRegistrationNumber();

        assertNotNull(registrationNumber);
        assertInstanceOf(Long.class, registrationNumber);
        assertEquals(1L, registrationNumber);
    }

    @DisplayName("Выбрасываем DocumentExistsException если сгенерированный регистрационный номер уже был сгенерирован ранее")
    @Test
    void getRegistrationNumber_Should_Return_DocumentExistsException() throws NoSuchFieldException, IllegalAccessException, DocumentExistsException {
        when(randomMock.nextDouble()).thenReturn(0.01);

        Field field = generateDataDocument.getClass().getDeclaredField("random");
        field.setAccessible(true);
        field.set(generateDataDocument, randomMock);

        Long registrationNumber = generateDataDocument.getRegistrationNumber();
        assertNotNull(registrationNumber);
        assertThrows(DocumentExistsException.class, () -> generateDataDocument.getRegistrationNumber());
    }

    @Test
    void getName() {
        Object name = generateDataDocument.getName();

        assertNotNull(name);
        assertInstanceOf(String.class, name);
    }

    @Test
    void getText() {
        Object text = generateDataDocument.getText();

        assertNotNull(text);
        assertInstanceOf(String.class, text);
    }

    @Test
    void getAuthor() {
        Person author = generateDataDocument.getAuthor();

        assertNotNull(author);
        assertNotNull(author.getName());
    }
    @DisplayName("Получаем псевдослучайную дату")
    @Test
    void getDateRegistration() {
        Object dateRegistration = generateDataDocument.getDateRegistration();

        assertNotNull(dateRegistration);
        assertInstanceOf(Date.class, dateRegistration);
    }
}