package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.entity.Task;
import com.example.document_flow.entity.person.Person;
import com.example.document_flow.factory.generator.DataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FactoryTaskTest {

    private DataGenerator mockDataGenerator = mock(DataGenerator.class);

    private final FactoryTask factoryTask = new FactoryTask();

    public FactoryTaskTest() throws IllegalAccessException, NoSuchFieldException {
        when(mockDataGenerator.getDateOfIssue()).thenReturn(new GregorianCalendar(2021, Calendar.NOVEMBER, 9).getTime());
        when(mockDataGenerator.getTermOfExecution()).thenReturn(new GregorianCalendar(2021, Calendar.DECEMBER, 9).getTime());
        when(mockDataGenerator.getResponsibleExecutor()).thenReturn(new Person("Розанова Полина Дмитриевна"));
        when(mockDataGenerator.getSignOfControl()).thenReturn("Признак контрольности 1");
        when(mockDataGenerator.getOrderController()).thenReturn(new Person("Горелова Анна Адамовна"));

        when(mockDataGenerator.getName()).thenReturn("Первый документ");
        when(mockDataGenerator.getAuthor()).thenReturn(new Person("Кошелева Василиса Ивановна"));
        when(mockDataGenerator.getDateRegistration()).thenReturn(new GregorianCalendar(2021, Calendar.OCTOBER, 9).getTime());
        when(mockDataGenerator.getText()).thenReturn("Text test");
        when(mockDataGenerator.getRegistrationNumber()).thenReturn(5L);

        Field field = factoryTask.getClass().getSuperclass().getDeclaredField("dataGenerator");
        field.setAccessible(true);
        field.set(factoryTask, mockDataGenerator);
    }

    @DisplayName("Создаем документ, Проверяем что все поля кроме id, не null")
    @Test
   public void creatDocument() throws IllegalAccessException {
        Task task = (Task) factoryTask.create();

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