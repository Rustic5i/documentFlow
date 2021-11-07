package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.generateDate.GenerateDataOutgoing;
import com.example.document_flow.model.Outgoing;
import com.example.document_flow.model.person.Person;
import com.example.document_flow.myException.DocumentExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class FactoryOutgoingTest {
    @Mock
    private GenerateDataOutgoing mockGenerateDataOutgoing;

    private FactoryOutgoing factoryOutgoing = new FactoryOutgoing();

    public FactoryOutgoingTest() {
        MockitoAnnotations.initMocks(this);

        when(mockGenerateDataOutgoing.getAddressee()).thenReturn("Амурская область, город Дорохово, ул. Косиора, 13");
        when(mockGenerateDataOutgoing.getDeliveryMethod()).thenReturn("Почта России");

        when(mockGenerateDataOutgoing.getName()).thenReturn("Первый документ");
        when(mockGenerateDataOutgoing.getAuthor()).thenReturn(new Person("Кошелева Василиса Ивановна"));
        when(mockGenerateDataOutgoing.getDateRegistration()).thenReturn(new GregorianCalendar(2021, 9, 9).getTime());
        when(mockGenerateDataOutgoing.getText()).thenReturn("Text test");
    }

    @DisplayName("Создаем документ, Проверяем что все поля кроме id, не null")
    @Test
    void creatDocument() throws DocumentExistsException, IllegalAccessException {
        when(mockGenerateDataOutgoing.getRegistrationNumber()).thenReturn(1L);

        FactoryOutgoing factoryOutgoingSpy = Mockito.spy(factoryOutgoing);
        Mockito.doReturn(mockGenerateDataOutgoing).when(factoryOutgoingSpy).makeGenerateDataIncoming();
        Outgoing outgoing = (Outgoing) factoryOutgoingSpy.creatDocument();

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

    @DisplayName("Если при создание документа был выброшено исключение DocumentExistsException, пробросить его дальше")
    @Test
    void trowsDocumentExistsException() throws DocumentExistsException {
        when(mockGenerateDataOutgoing.getRegistrationNumber()).thenThrow(DocumentExistsException.class);

        FactoryOutgoing factoryOutgoingSpy = Mockito.spy(factoryOutgoing);
        Mockito.doReturn(mockGenerateDataOutgoing).when(factoryOutgoingSpy).makeGenerateDataIncoming();

        assertThrows(DocumentExistsException.class, () -> factoryOutgoingSpy.creatDocument());
    }
}