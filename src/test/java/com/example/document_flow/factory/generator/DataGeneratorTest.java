package com.example.document_flow.factory.generator;

import com.example.document_flow.entity.staff.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataGeneratorTest {

    private final Random randomMock = mock(Random.class);

    private final DataGenerator dataGenerator = DataGenerator.getInstance();

    public DataGeneratorTest() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("Получаем псевдослучайный регистрационный номер")
    @Test
    public void getRegistrationNumber() throws NoSuchFieldException, IllegalAccessException {
        when(randomMock.nextDouble()).thenReturn(0.01);

        Field field = dataGenerator.getClass().getDeclaredField("RANDOM");
        field.setAccessible(true);
        field.set(dataGenerator, randomMock);

        Object registrationNumber = dataGenerator.getRegistrationNumber();

        assertNotNull(registrationNumber);
        assertInstanceOf(Long.class, registrationNumber);
        assertEquals(10L, registrationNumber);
    }

    @Test
    public void getName() {
        Object name = dataGenerator.getName();

        assertNotNull(name);
        assertInstanceOf(String.class, name);
    }

    @Test
    public void getText() {
        Object text = dataGenerator.getText();

        assertNotNull(text);
        assertInstanceOf(String.class, text);
    }

    @Test
    public void getAuthor() {
        Person author = dataGenerator.getPerson();

        assertNotNull(author);
        assertNotNull(author.getName());
    }

    @Test
    public void getDateRegistration() {
        Object dateRegistration = dataGenerator.getDateRegistration();

        assertNotNull(dateRegistration);
        assertInstanceOf(Date.class, dateRegistration);
    }

    @Test
    public void generateOutgoingRegistrationDate() {
        Date registrationDate = dataGenerator.getOutgoingRegistrationDate();

        assertNotNull(registrationDate);
    }

    @Test
    public void getSource() {
        Person source = dataGenerator.getSource();

        assertNotNull(source);
        assertNotNull(source.getName());
    }

    @Test
    public void getAddressee() {
        Object addressee = dataGenerator.getAddressee();

        assertNotNull(addressee);
        assertInstanceOf(String.class, addressee);
    }

    @Test
    public void getOutgoingNumber() {
        Long outgoingNumber = dataGenerator.getOutgoingNumber();

        assertNotNull(outgoingNumber);
    }

    @Test
    public void getDeliveryMethod() {
        Object deliveryMethod = dataGenerator.getDeliveryMethod();

        assertNotNull(deliveryMethod);
        assertInstanceOf(String.class, deliveryMethod);
    }

    @Test
    public void getTermOfExecution() {
        Date termOfExecution = dataGenerator.getTermOfExecution();

        assertNotNull(termOfExecution);
    }

    @Test
    public void getDateOfIssue() {
        Date dateOfIssue = dataGenerator.getDateOfIssue();

        assertNotNull(dateOfIssue);
    }

    @Test
    public void getResponsibleExecutor() {
        Person responsibleExecutor = dataGenerator.getResponsibleExecutor();

        assertNotNull(responsibleExecutor);
        assertNotNull(responsibleExecutor.getName());
    }

    @Test
    public void getSignOfControl() {
        Object signOfControl = dataGenerator.getSignOfControl();

        assertNotNull(signOfControl);
        assertInstanceOf(String.class, signOfControl);
    }

    @Test
    public void getOrderController() {
        Object orderController = dataGenerator.getOrderController();

        assertNotNull(orderController);
        assertInstanceOf(Person.class, orderController);
    }

    @DisplayName("DataGenerator должен быть синглтон")
    @Test
    public void getInstance() {
        DataGenerator actual = DataGenerator.getInstance();

        assertNotNull(actual);
        assertSame(dataGenerator, actual);
    }
}