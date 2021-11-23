package com.example.document_flow.factory.document;

import com.example.document_flow.entity.document.Task;

/**
 * Класс-фабрика для генерации Входящего документа (Task)
 *
 * @author Баратов Руслан
 */
public class TaskDocumentFactory extends AbstractDocumentFactory<Task> {

    /**
     * Фабричный метод для создания объекта класса <code>Task</code>
     *
     * @return новый инстанс класса <code>Task</code>
     */
    @Override
    public Task createInstance() {
        return new Task();
    }

    /**
     * Поля объекта заполняются случайными значениями
     * @param task документ-поручение
     */
    @Override
    public void fillAdditionalFields(Task task) {
        task.setDateOfIssue(getDataGenerator().getDateOfIssue());
        task.setTermOfExecution(getDataGenerator().getTermOfExecution());
        task.setResponsibleExecutor(getDataGenerator().getResponsibleExecutor());
        task.setSignOfControl(getDataGenerator().getSignOfControl());
        task.setOrderController(getDataGenerator().getOrderController());
    }

    /**
     * @return Тип создваемого документа
     */
    @Override
    public Class getTypeObject() {
        return Task.class;
    }

}
