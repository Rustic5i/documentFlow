package com.example.document_flow.factory.document;

import com.example.document_flow.entity.document.Outgoing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OutgoingDocumentFactoryTest {

    private final OutgoingDocumentFactory factoryOutgoing = new OutgoingDocumentFactory();

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