package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.generateDate.GenerateDataIncoming;
import com.example.document_flow.model.Incoming;
import com.example.document_flow.model.person.Person;
import com.example.document_flow.myException.DocumentExistsException;
import lombok.SneakyThrows;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.GregorianCalendar;
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
        when(mockGenerateDataIncoming.generateDate()).thenReturn(new GregorianCalendar(2021, 10, 10).getTime());
        when(mockGenerateDataIncoming.getName()).thenReturn("Первый документ");
        when(mockGenerateDataIncoming.getAuthor()).thenReturn(new Person("Кошелева Василиса Ивановна"));
        when(mockGenerateDataIncoming.getDateRegistration()).thenReturn(new GregorianCalendar(2021, 9, 9).getTime());
        when(mockGenerateDataIncoming.getRandomRegNumber()).thenReturn(1L);
        when(mockGenerateDataIncoming.getText()).thenReturn("Text test");
    }
    //1,Каждый раз новый документ
    //2, документы с уникальным регистрационым номером
    //3, документ пуст, все поля заполнены



    @DisplayName("Создать документ, Проверяем что все поля кроме id, заполнены")
    @Test
    void creatDocument() throws DocumentExistsException, IllegalAccessException {

        FactoryIncoming factoryIncomingSpy = Mockito.spy(factoryIncoming);
        Mockito.doReturn(mockGenerateDataIncoming).when(factoryIncomingSpy).makeGenerateDataIncoming();
        Incoming incoming = (Incoming) factoryIncomingSpy.creatDocument();

        Field[] fieldSuperClass = incoming.getClass().getSuperclass().getDeclaredFields();
        Field[] fieldsIncomingClass = incoming.getClass().getDeclaredFields();

        Assert.assertNotNull(incoming);

        for (Field field: fieldSuperClass) {
            field.setAccessible(true);
            if (!field.getName().equals("id")){
                Assert.assertNotNull(field.get(incoming));
            }
        }
        for (Field field: fieldsIncomingClass) {
            field.setAccessible(true);
            Assert.assertNotNull(field.get(incoming));
        }
    }

    @DisplayName("Документ должен быть уникальный, с уникальным регистрационым номером")
    @Test
    void checkForUniquenessIncoming() {
    }

    @DisplayName("Если при создание документа был выбрашено исключение DocumentExistsException, пробрось его дальше")
    @Test
    void trowsDocumentExistsException() {

    }
}