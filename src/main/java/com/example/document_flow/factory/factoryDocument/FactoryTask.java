package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.abstr.Factory;
import com.example.document_flow.factory.generateDate.GenerateDataTask;
import com.example.document_flow.model.Document;
import com.example.document_flow.model.Task;
import com.example.document_flow.myException.DocumentExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>FactoryTask</code> класс-фабрика для генерации Входящего документа (Task)
 */
public class FactoryTask extends FactoryDocument implements Factory {

    private GenerateDataTask generateDataTask = new GenerateDataTask();

    private static final Logger log = LoggerFactory.getLogger(FactoryOutgoing.class.getName());

    @Override
    public Document creatDocument() {
        Task task = new Task();
        try {
            task = (Task) getRandomInstance(task);
            task.setDateOfIssue(generateDataTask.getDateOfIssue());
            task.setTermOfExecution(generateDataTask.getTermOfExecution());
            task.setResponsibleExecutor(generateDataTask.getResponsibleExecutor());
            task.setSignOfControl(generateDataTask.getSignOfControl());
            task.setOrderController(generateDataTask.getOrderController());
            return task;
        } catch (DocumentExistsException e) {
            log.warn("Exception ", e);
        }
        return null;
    }
}
