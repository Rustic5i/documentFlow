package com.example.document_flow.factory.generateDate;

import com.example.document_flow.myException.DocumentExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GenerateDataDocumentTest {


//    @Mock
//    private Set<Long> registrationNumber;//регистрационный номер документа

    private GenerateDataDocument generateDataDocument = new GenerateDataDocument();

    public GenerateDataDocumentTest() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("Получаем псевдорандомный регистрационный номер")
    @Test
    void getRegistrationNumber() throws DocumentExistsException, NoSuchFieldException, IllegalAccessException {
        Random randomMock = Mockito.mock(Random.class);
        when(randomMock.nextDouble()).thenReturn(0.01);

        Field field = generateDataDocument.getClass().getDeclaredField("random");
        field.setAccessible(true);
        field.set(generateDataDocument,randomMock);

        Long registrationNumber = generateDataDocument.getRegistrationNumber();
        assertEquals(1,registrationNumber);
    }

    @DisplayName("Получаем псевдорандомный регистрационный номер")
    @Test
    void getRegistrationNumber_Should_Return_DocumentExistsException() throws NoSuchFieldException, IllegalAccessException {
        Random randomMock = Mockito.mock(Random.class);
        when(randomMock.nextDouble()).thenReturn(0.01);

        Field field = generateDataDocument.getClass().getDeclaredField("random");
        field.setAccessible(true);
        field.set(generateDataDocument,randomMock);


    }


    @Test
    void getName() {
    }

    @Test
    void getText() {
    }

    @Test
    void getAuthor() {
    }

    @Test
    void getDateRegistration() {
    }
}