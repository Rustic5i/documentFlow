package com.example.document_flow.factory.document;

import com.example.document_flow.entity.document.Task;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.factory.generator.DataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaskDocumentFactoryTest {

    private DataGenerator mockDataGenerator = mock(DataGenerator.class);

    private final TaskDocumentFactory factoryTask = new TaskDocumentFactory();

    public TaskDocumentFactoryTest() throws IllegalAccessException, NoSuchFieldException {
        Person person = new Person();
        person.setId(2);
        person.setName("Андрей");
        person.setSurname("Сабиров");
        person.setPatronymic("Васильевич");
        person.setPost("Бугалтер");
        person.setDateOfBirth(new GregorianCalendar(1998, 12, 12).getTime());
        person.setPhoneNumber(866555445);

        when(mockDataGenerator.getDateOfIssue()).thenReturn(new GregorianCalendar(2021, Calendar.NOVEMBER, 9).getTime());
        when(mockDataGenerator.getTermOfExecution()).thenReturn(new GregorianCalendar(2021, Calendar.DECEMBER, 9).getTime());
        when(mockDataGenerator.getResponsibleExecutor()).thenReturn(person);
        when(mockDataGenerator.getSignOfControl()).thenReturn("Признак контрольности 1");
        when(mockDataGenerator.getOrderController()).thenReturn(person);

        when(mockDataGenerator.getName()).thenReturn("Первый документ");
        when(mockDataGenerator.getAuthor()).thenReturn(person);
        when(mockDataGenerator.getDateRegistration()).thenReturn(new GregorianCalendar(2021, Calendar.OCTOBER, 9).getTime());
        when(mockDataGenerator.getText()).thenReturn("Text test");
        when(mockDataGenerator.getRegistrationNumber()).thenReturn(5L);

        Field field = factoryTask.getClass().getSuperclass().getDeclaredField("dataGenerator");
        field.setAccessible(true);
        field.set(factoryTask, mockDataGenerator);
    }

    @DisplayName("Создаем документ, Проверяем что все поля кроме id, не null")
    @Test
    void creatDocument() throws IllegalAccessException {
        Task task = factoryTask.create();

        Field[] fieldSuperClass = task.getClass().getSuperclass().getDeclaredFields();
        Field[] fieldsIncomingClass = task.getClass().getDeclaredFields();

        assertNotNull(task);

        for (Field field : fieldSuperClass) {
            field.setAccessible(true);
            if (!field.getName().equals("id")) {
                assertNotNull(field.get(task));
            }
        }
        for (Field field : fieldsIncomingClass) {
            field.setAccessible(true);
            assertNotNull(field.get(task));
        }
    }

}