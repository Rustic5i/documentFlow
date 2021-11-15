package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.document.Document;
import com.example.document_flow.document.Incoming;
import com.example.document_flow.document.person.Person;
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

class FactoryIncomingTest {

    @Mock
    private DataGenerator mockDataGenerator = mock(DataGenerator.class);

    private final FactoryIncoming factoryIncoming = new FactoryIncoming();

    public FactoryIncomingTest() throws NoSuchFieldException, IllegalAccessException {
        when(mockDataGenerator.getSource()).thenReturn(new Person("Кошелева Василиса Ивановна"));
        when(mockDataGenerator.getAddressee()).thenReturn("Астраханская область, город Щёлково, шоссе Косиора, 30");
        when(mockDataGenerator.getOutgoingNumber()).thenReturn(100L);
        when(mockDataGenerator.getOutgoingRegistrationDate()).thenReturn(new GregorianCalendar(2021, Calendar.NOVEMBER, 10).getTime());
        when(mockDataGenerator.getName()).thenReturn("Первый документ");
        when(mockDataGenerator.getAuthor()).thenReturn(new Person("Кошелева Василиса Ивановна"));
        when(mockDataGenerator.getDateRegistration()).thenReturn(new GregorianCalendar(2021, Calendar.OCTOBER, 9).getTime());
        when(mockDataGenerator.getText()).thenReturn("Text test");
        when(mockDataGenerator.getRegistrationNumber()).thenReturn(1L);

        Field field = factoryIncoming.getClass().getSuperclass().getDeclaredField("dataGenerator");
        field.setAccessible(true);
        field.set(factoryIncoming, mockDataGenerator);
    }

    @DisplayName("Создаем документ, Проверяем что все поля кроме id, не null")
    @Test
    void creatDocument() throws DocumentExistsException, IllegalAccessException {
        Incoming incoming = (Incoming) factoryIncoming.createDocument();

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

    @DisplayName("В случае, если документ с генерируемым номером уже существует, то необходимо выбрасывать исключение DocumentExistsException")
    @Test
    void trowsDocumentExistsException() throws DocumentExistsException {
        when(mockDataGenerator.getRegistrationNumber()).thenReturn(2L);

        Document firstDocument = factoryIncoming.createDocument();

        assertThrows(DocumentExistsException.class, factoryIncoming::createDocument);
    }
}