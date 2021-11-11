package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.abstr.Factory;
import com.example.document_flow.document.Document;
import com.example.document_flow.document.Task;
import com.example.document_flow.myException.DocumentExistsException;

/**
 * <code>FactoryTask</code> класс-фабрика для генерации Входящего документа (Task)
 */
public class FactoryTask extends FactoryDocument implements Factory {

    /**
     * Фабричный метод для создания объекта класса <code>Task</code>
     * поля объекта заполняются случайными значениями
     *
     * @return document с заполненными, рандомными значениями
     * @throws DocumentExistsException если документ с генерируемым регистрационным номером уже существует
     */
    @Override
    public Document creatDocument() throws DocumentExistsException {
        Task task = new Task();
        task = (Task) getRandomInstance(task);
        task.setDateOfIssue(super.getDataGenerator().getDateOfIssue());
        task.setTermOfExecution(super.getDataGenerator().getTermOfExecution());
        task.setResponsibleExecutor(super.getDataGenerator().getResponsibleExecutor());
        task.setSignOfControl(super.getDataGenerator().getSignOfControl());
        task.setOrderController(super.getDataGenerator().getOrderController());
        return task;
    }

}
