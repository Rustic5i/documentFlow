package com.example.document_flow.factory.documentFactory;

import com.example.document_flow.entity.document.Outgoing;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.factory.document.OutgoingDocumentFactory;
import com.example.document_flow.factory.generator.DataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OutgoingDocumentFactoryTest {

    private DataGenerator mockDataGenerator = mock(DataGenerator.class);

    private final OutgoingDocumentFactory factoryOutgoing = new OutgoingDocumentFactory();

    public OutgoingDocumentFactoryTest() throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person();
        person.setId(1);
        person.setName("Василий");
        person.setSurname("Воробьев");
        person.setPatronymic("Андреевич");
        person.setPost("Юрист");
        person.setDateOfBirth(new GregorianCalendar(1995, 02, 1).getTime());
        person.setPhoneNumber(899944444);

        when(mockDataGenerator.getAddressee()).thenReturn("Амурская область, город Дорохово, ул. Косиора, 13");
        when(mockDataGenerator.getDeliveryMethod()).thenReturn("Почта России");

        when(mockDataGenerator.getName()).thenReturn("Первый документ");
        when(mockDataGenerator.getAuthor()).thenReturn(person);
        when(mockDataGenerator.getDateRegistration()).thenReturn(new GregorianCalendar(2021, Calendar.OCTOBER, 9).getTime());
        when(mockDataGenerator.getText()).thenReturn("Text test");
        when(mockDataGenerator.getRegistrationNumber()).thenReturn(3L);

        Field field = factoryOutgoing.getClass().getSuperclass().getDeclaredField("dataGenerator");
        field.setAccessible(true);
        field.set(factoryOutgoing, mockDataGenerator);
    }

    @DisplayName("Создаем документ, Проверяем что все поля кроме id, не null")
    @Test
    void creatDocument() throws IllegalAccessException {
        Outgoing outgoing = factoryOutgoing.create();

        Field[] fieldSuperClass = outgoing.getClass().getSuperclass().getDeclaredFields();
        Field[] fieldsIncomingClass = outgoing.getClass().getDeclaredFields();

        assertNotNull(outgoing);

        for (Field field : fieldSuperClass) {
            field.setAccessible(true);
            if (!field.getName().equals("id")) {
                assertNotNull(field.get(outgoing));
            }
        }
        for (Field field : fieldsIncomingClass) {
            field.setAccessible(true);
            assertNotNull(field.get(outgoing));
        }
    }


}