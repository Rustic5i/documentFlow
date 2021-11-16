package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.entity.Document;
import com.example.document_flow.entity.Outgoing;
import com.example.document_flow.entity.person.Person;
import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.myException.DocumentExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FactoryOutgoingTest {

    @Mock
    private DataGenerator mockDataGenerator = mock(DataGenerator.class);

    private final FactoryOutgoing factoryOutgoing = new FactoryOutgoing();

    public FactoryOutgoingTest() throws NoSuchFieldException, IllegalAccessException {
        when(mockDataGenerator.getAddressee()).thenReturn("Амурская область, город Дорохово, ул. Косиора, 13");
        when(mockDataGenerator.getDeliveryMethod()).thenReturn("Почта России");

        when(mockDataGenerator.getName()).thenReturn("Первый документ");
        when(mockDataGenerator.getAuthor()).thenReturn(new Person("Кошелева Василиса Ивановна"));
        when(mockDataGenerator.getDateRegistration()).thenReturn(new GregorianCalendar(2021, Calendar.OCTOBER, 9).getTime());
        when(mockDataGenerator.getText()).thenReturn("Text test");
        when(mockDataGenerator.getRegistrationNumber()).thenReturn(3L);

        Field field = factoryOutgoing.getClass().getSuperclass().getDeclaredField("dataGenerator");
        field.setAccessible(true);
        field.set(factoryOutgoing, mockDataGenerator);
    }

    @DisplayName("Создаем документ, Проверяем что все поля кроме id, не null")
    @Test
    void creatDocument() throws DocumentExistsException, IllegalAccessException {
        Outgoing outgoing = (Outgoing) factoryOutgoing.createDocument();

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

    @DisplayName("В случае, если документ с генерируемым номером уже существует, то необходимо выбрасывать исключение DocumentExistsException")
    @Test
    void trowsDocumentExistsException() throws DocumentExistsException {
        when(mockDataGenerator.getRegistrationNumber()).thenReturn(4L);

        Document firstDocument = factoryOutgoing.createDocument();

        assertThrows(DocumentExistsException.class, factoryOutgoing::createDocument);
    }
}