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
        task.newBuilder()
                .setDateOfIssue(getDATA_GENERATOR().getDateOfIssue())
                .setTermOfExecution(getDATA_GENERATOR().getTermOfExecution())
                .setResponsibleExecutor(getDATA_GENERATOR().getResponsibleExecutor())
                .setSignOfControl(getDATA_GENERATOR().getSignOfControl())
                .setOrderController(getDATA_GENERATOR().getOrderController());
    }

    /**
     * @return Тип создаваемого документа
     */
    @Override
    public Class getTypeObject() {
        return Task.class;
    }
}
