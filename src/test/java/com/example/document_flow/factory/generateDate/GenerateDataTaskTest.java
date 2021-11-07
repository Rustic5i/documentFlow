package com.example.document_flow.factory.generateDate;

import com.example.document_flow.model.person.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GenerateDataTaskTest {

    private GenerateDataTask generateDataTask = GenerateDataTask.getInstance();

    @Test
    void getTermOfExecution() {
        Date termOfExecution = generateDataTask.getTermOfExecution();

        assertNotNull(termOfExecution);
    }

    @Test
    void getDateOfIssue() {
        Date dateOfIssue = generateDataTask.getDateOfIssue();

        assertNotNull(dateOfIssue);
    }

    @Test
    void getResponsibleExecutor() {
        Person responsibleExecutor = generateDataTask.getResponsibleExecutor();

        assertNotNull(responsibleExecutor);
        assertNotNull(responsibleExecutor.getName());
    }

    @Test
    void getSignOfControl() {
        Object signOfControl = generateDataTask.getSignOfControl();

        assertNotNull(signOfControl);
        assertInstanceOf(String.class, signOfControl);
    }

    @Test
    void getOrderController() {
        Object orderController = generateDataTask.getOrderController();

        assertNotNull(orderController);
        assertInstanceOf(String.class, orderController);
    }

    @DisplayName("GenerateDataTask должен быть синглтон")
    @Test
    void getInstance() {
        GenerateDataTask actual = GenerateDataTask.getInstance();

        assertNotNull(actual);
        assertSame(generateDataTask,actual);
    }
}