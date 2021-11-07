package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.generateDate.GenerateDataIncoming;
import com.example.document_flow.model.Incoming;
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
import static org.mockito.Mockito.when;

class FactoryIncomingTest {

    @Mock
    private GenerateDataIncoming mockGenerateDataIncoming;

    private FactoryIncoming factoryIncoming = new FactoryIncoming();

    public FactoryIncomingTest() throws DocumentExistsException {
        MockitoAnnotations.initMocks(this);

        when(mockGenerateDataIncoming.getSource()).thenReturn(new Person("Кошелева Василиса Ивановна"));
        when(mockGenerateDataIncoming.getAddressee()).thenReturn("Астраханская область, город Щёлково, шоссе Косиора, 30");
        when(mockGenerateDataIncoming.getOutgoingNumber()).thenReturn(100L);
        when(mockGenerateDataIncoming.generateOutgoingRegistrationDate()).thenReturn(new GregorianCalendar(2021, 10, 10).getTime());
        when(mockGenerateDataIncoming.getName()).thenReturn("Первый документ");
        when(mockGenerateDataIncoming.getAuthor()).thenReturn(new Person("Кошелева Василиса Ивановна"));
        when(mockGenerateDataIncoming.getDateRegistration()).thenReturn(new GregorianCalendar(2021, 9, 9).getTime());
        when(mockGenerateDataIncoming.getRegistrationNumber()).thenReturn(1L);
        when(mockGenerateDataIncoming.getText()).thenReturn("Text test");
    }
    //1,Каждый раз новый документ
    //2, документы с уникальным регистрационым номером
    //3, документ пуст, все поля заполнены

    @DisplayName("Создаем документ, Проверяем что все поля кроме id, не null")
    @Test
    void creatDocument() throws DocumentExistsException, IllegalAccessException {
        FactoryIncoming factoryIncomingSpy = Mockito.spy(factoryIncoming);
        Mockito.doReturn(mockGenerateDataIncoming).when(factoryIncomingSpy).makeGenerateDataIncoming();
        Incoming incoming = (Incoming) factoryIncomingSpy.creatDocument();

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

    @DisplayName("Если при создание документа был выброшено исключение DocumentExistsException, пробрось его дальше")
    @Test
    void trowsDocumentExistsException() {

    }
}