package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.generateDate.GenerateDataTask;
import com.example.document_flow.model.Task;
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

class FactoryTaskTest {

    @Mock
    private GenerateDataTask mockGenerateDataTask;

    private FactoryTask factoryTask = new FactoryTask();

    public FactoryTaskTest() {
        MockitoAnnotations.initMocks(this);

        when(mockGenerateDataTask.getDateOfIssue()).thenReturn(new GregorianCalendar(2021, 10, 9).getTime());
        when(mockGenerateDataTask.getTermOfExecution()).thenReturn(new GregorianCalendar(2021, 11, 9).getTime());
        when(mockGenerateDataTask.getResponsibleExecutor()).thenReturn(new Person("Розанова Полина Дмитриевна"));
        when(mockGenerateDataTask.getSignOfControl()).thenReturn("Признак контрольности 1");
        when(mockGenerateDataTask.getOrderController()).thenReturn("Горелова Анна Адамовна");

        when(mockGenerateDataTask.getName()).thenReturn("Первый документ");
        when(mockGenerateDataTask.getAuthor()).thenReturn(new Person("Кошелева Василиса Ивановна"));
        when(mockGenerateDataTask.getDateRegistration()).thenReturn(new GregorianCalendar(2021, 9, 9).getTime());
        when(mockGenerateDataTask.getText()).thenReturn("Text test");
    }

    @DisplayName("Создаем документ, Проверяем что все поля кроме id, не null")
    @Test
    void creatDocument() throws DocumentExistsException, IllegalAccessException {
        when(mockGenerateDataTask.getRegistrationNumber()).thenReturn(1L);

        FactoryTask factoryTaskSpy = Mockito.spy(factoryTask);
        Mockito.doReturn(mockGenerateDataTask).when(factoryTaskSpy).makeGenerateDataIncoming();
        Task task = (Task) factoryTaskSpy.creatDocument();

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

    @DisplayName("Если при создание документа был выброшено исключение DocumentExistsException, пробросить его дальше")
    @Test
    void trowsDocumentExistsException() throws DocumentExistsException {
        when(mockGenerateDataTask.getRegistrationNumber()).thenThrow(DocumentExistsException.class);

        FactoryTask factoryTaskSpy = Mockito.spy(factoryTask);
        Mockito.doReturn(mockGenerateDataTask).when(factoryTaskSpy).makeGenerateDataIncoming();

        assertThrows(DocumentExistsException.class, () -> factoryTaskSpy.creatDocument());
    }
}