package com.example.document_flow.factory.generator;

import com.example.document_flow.document.person.Person;
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

class DataGeneratorTest {

    @Mock
    private Random randomMock = Mockito.mock(Random.class);

    private DataGenerator dataGenerator = DataGenerator.getInstance();

    public DataGeneratorTest() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("Получаем псевдослучайный регистрационный номер")
    @Test
    void getRegistrationNumber() throws NoSuchFieldException, IllegalAccessException {
        when(randomMock.nextDouble()).thenReturn(0.01);

        Field field = dataGenerator.getClass().getDeclaredField("random");
        field.setAccessible(true);
        field.set(dataGenerator, randomMock);

        Object registrationNumber = dataGenerator.getRegistrationNumber();

        assertNotNull(registrationNumber);
        assertInstanceOf(Long.class, registrationNumber);
        assertEquals(1L, registrationNumber);
    }

    @Test
    void getName() {
        Object name = dataGenerator.getName();

        assertNotNull(name);
        assertInstanceOf(String.class, name);
    }

    @Test
    void getText() {
        Object text = dataGenerator.getText();

        assertNotNull(text);
        assertInstanceOf(String.class, text);
    }

    @Test
    void getAuthor() {
        Person author = dataGenerator.getAuthor();

        assertNotNull(author);
        assertNotNull(author.getName());
    }

    @Test
    void getDateRegistration() {
        Object dateRegistration = dataGenerator.getDateRegistration();

        assertNotNull(dateRegistration);
        assertInstanceOf(Date.class, dateRegistration);
    }

    @Test
    void generateOutgoingRegistrationDate() {
        Date registrationDate = dataGenerator.getOutgoingRegistrationDate();

        assertNotNull(registrationDate);
    }

    @Test
    void getSource() {
        Person source = dataGenerator.getSource();

        assertNotNull(source);
        assertNotNull(source.getName());
    }

    @Test
    void getAddressee() {
        Object addressee = dataGenerator.getAddressee();

        assertNotNull(addressee);
        assertInstanceOf(String.class, addressee);
    }

    @Test
    void getOutgoingNumber() {
        Long outgoingNumber = dataGenerator.getOutgoingNumber();

        assertNotNull(outgoingNumber);
    }

    @Test
    void getDeliveryMethod() {
        Object deliveryMethod = dataGenerator.getDeliveryMethod();

        assertNotNull(deliveryMethod);
        assertInstanceOf(String.class, deliveryMethod);
    }

    @Test
    void getTermOfExecution() {
        Date termOfExecution = dataGenerator.getTermOfExecution();

        assertNotNull(termOfExecution);
    }

    @Test
    void getDateOfIssue() {
        Date dateOfIssue = dataGenerator.getDateOfIssue();

        assertNotNull(dateOfIssue);
    }

    @Test
    void getResponsibleExecutor() {
        Person responsibleExecutor = dataGenerator.getResponsibleExecutor();

        assertNotNull(responsibleExecutor);
        assertNotNull(responsibleExecutor.getName());
    }

    @Test
    void getSignOfControl() {
        Object signOfControl = dataGenerator.getSignOfControl();

        assertNotNull(signOfControl);
        assertInstanceOf(String.class, signOfControl);
    }

    @Test
    void getOrderController() {
        Object orderController = dataGenerator.getOrderController();

        assertNotNull(orderController);
        assertInstanceOf(Person.class, orderController);
    }

    @DisplayName("DataGenerator должен быть синглтон")
    @Test
    void getInstance() {
        DataGenerator actual = DataGenerator.getInstance();

        assertNotNull(actual);
        assertSame(dataGenerator, actual);
    }
}