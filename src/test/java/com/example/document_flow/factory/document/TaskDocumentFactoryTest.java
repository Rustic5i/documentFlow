package com.example.document_flow.factory.document;

import com.example.document_flow.entity.document.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskDocumentFactoryTest {

    private final TaskDocumentFactory factoryTask = new TaskDocumentFactory();

    @DisplayName("Создаем документ, Проверяем что все поля кроме id, не null")
    @Test
    void creatDocument() throws IllegalAccessException {
        Task task = factoryTask.create();

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