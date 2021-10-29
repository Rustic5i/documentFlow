package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.generateDate.GenerateDataDocument;
import com.example.document_flow.factory.generateDate.GenerateDataIncoming;
import com.example.document_flow.model.Incoming;
import com.example.document_flow.model.person.Person;
import com.example.document_flow.myException.DocumentExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = FactoryIncoming.class)
class FactoryIncomingTest {

    @MockBean
    private GenerateDataIncoming generateDataIncoming;

    @MockBean
    private GenerateDataDocument generateDataDocument;

    @MockBean
    private FactoryDocument factoryDocument;

    private FactoryIncoming factory = new FactoryIncoming();

    private final Person PERSON = new Person("Кошелева Василиса Ивановна");

    private final String ADDRESS = "Астраханская область, город Щёлково, шоссе Косиора, 30";

    private final Date DATE = new GregorianCalendar(2021, 15, 28).getTime();

    private final Incoming actual = new Incoming();


    @DisplayName("Создание документа на основе предоставляющих данных обьектом GenerateDataIncoming")
    @Test
    void creatDocument() {
        when(generateDataIncoming.getSource()).thenReturn(PERSON);
        when(generateDataIncoming.getAddressee()).thenReturn(ADDRESS);

        when(generateDataDocument.getName()).thenReturn("dwqd");

        assertThrows(DocumentExistsException.class,
                () -> factory.creatDocument());
       // when()
//        when(generateDataIncoming.generateDate()).thenReturn(DATE);
//        when(generateDataIncoming.getOutgoingNumber()).thenReturn(1554L);

        Incoming incoming = (Incoming) factory.creatDocument();

        actual.setSource(PERSON);
        actual.setAddressee(ADDRESS);

        assertEquals(incoming, actual);
    }
}