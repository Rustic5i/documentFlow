package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.entity.Document;
import com.example.document_flow.entity.Task;
import com.example.document_flow.factory.abstr.Factory;

/**
 * <code>FactoryTask</code> класс-фабрика для генерации Входящего документа (Task)
 *
 * @author Баратов Руслан
 */
public class FactoryTask extends MapperDocument implements Factory {

    /**
     * Фабричный метод для создания объекта класса <code>Task</code>
     * поля объекта заполняются случайными значениями
     *
     * @return document с заполненными, рандомными значениями
     */
    @Override
    public Document createDocument() {
        Task task = new Task();
        fillTheBasicData(task);
        task.setDateOfIssue(getDataGenerator().getDateOfIssue());
        task.setTermOfExecution(getDataGenerator().getTermOfExecution());
        task.setResponsibleExecutor(getDataGenerator().getResponsibleExecutor());
        task.setSignOfControl(getDataGenerator().getSignOfControl());
        task.setOrderController(getDataGenerator().getOrderController());
        return task;
    }

}
