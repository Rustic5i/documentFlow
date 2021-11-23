package com.example.document_flow.factory.document;

import com.example.document_flow.entity.document.Incoming;
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

public class IncomingDocumentFactoryTest {

    private DataGenerator mockDataGenerator = mock(DataGenerator.class);

    private final IncomingDocumentFactory factoryIncoming = new IncomingDocumentFactory();

    public IncomingDocumentFactoryTest() throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person();
        person.setId(1);
        person.setName("Василий");
        person.setSurname("Воробьев");
        person.setPatronymic("Андреевич");
        person.setPost("Юрист");
        person.setDateOfBirth(new GregorianCalendar(1995, 02, 1).getTime());
        person.setPhoneNumber(899944444);

        when(mockDataGenerator.getSource()).thenReturn(person);
        when(mockDataGenerator.getAddressee()).thenReturn("Астраханская область, город Щёлково, шоссе Косиора, 30");
        when(mockDataGenerator.getOutgoingNumber()).thenReturn(100L);
        when(mockDataGenerator.getOutgoingRegistrationDate()).thenReturn(new GregorianCalendar(2021, Calendar.NOVEMBER, 10).getTime());
        when(mockDataGenerator.getName()).thenReturn("Первый документ");
        when(mockDataGenerator.getPerson()).thenReturn(person);
        when(mockDataGenerator.getDateRegistration()).thenReturn(new GregorianCalendar(2021, Calendar.OCTOBER, 9).getTime());
        when(mockDataGenerator.getText()).thenReturn("Text test");
        when(mockDataGenerator.getRegistrationNumber()).thenReturn(1L);

        Field field = factoryIncoming.getClass().getSuperclass().getDeclaredField("dataGenerator");
        field.setAccessible(true);
        field.set(factoryIncoming, mockDataGenerator);
    }

    @DisplayName("Создаем документ, Проверяем что все поля кроме id, не null")
    @Test
    void creatDocument() throws IllegalAccessException {
        Incoming incoming = factoryIncoming.create();

        Field[] fieldSuperClass = incoming.getClass().getSuperclass().getDeclaredFields();
        Field[] fieldsIncomingClass = incoming.getClass().getDeclaredFields();

        assertNotNull(incoming);

        for (Field field : fieldSuperClass) {
            field.setAccessible(true);
            if (!field.getName().equals("id")) {
                assertNotNull(field.get(incoming));
            }
        }
        for (Field field : fieldsIncomingClass) {
            field.setAccessible(true);
            assertNotNull(field.get(incoming));
        }
    }

}