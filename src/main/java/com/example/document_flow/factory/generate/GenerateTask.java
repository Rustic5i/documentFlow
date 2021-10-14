package com.example.document_flow.factory.generate;

import com.example.document_flow.model.person.Person;
import com.example.document_flow.model.person.Persons;
import com.example.document_flow.model.Task;
import com.example.document_flow.myException.DocumentExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

abstract class GenerateTask extends GenerateDocument {

    private static final Long DAY = 86400000L;

    private static Random random = new Random();

    private static Date dateOfIssue; //дата выдачи поручения

    private static Date termOfExecution; //срок исполнения поручения

    private static List<Person> responsibleExecutor; //ответственный исполнитель

    private static List<String> signOfControl;  //признак контрольности

    private static List<String> orderController;


    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        dateOfIssue = calendar.getTime();

        termOfExecution = new Date();

        responsibleExecutor = Persons.names;

        signOfControl = new ArrayList<>();
        signOfControl.add("Признак контрольности 1");
        signOfControl.add("Признак контрольности 2");
        signOfControl.add("Признак контрольности 3");

        orderController = new ArrayList<>();
        orderController.add("Давыдов Александр Георгиевич");
        orderController.add("Горелова Анна Адамовна");
        orderController.add("Баженова Виктория Михайловна");
        orderController.add("Баженова Александра Егоровна");
        orderController.add("Алексеев Глеб Сергеевич");
    }

    private static Date getTermOfExecution() {
        termOfExecution.setTime(dateOfIssue.getTime() + DAY * random.nextLong(10));
        return termOfExecution;
    }

    static Task getRandomInstance() {
        Task task = new Task();
        try {
            task = (Task) getRandomInstance(task);
            task.setDateOfIssue(dateOfIssue);
            task.setTermOfExecution(getTermOfExecution());
            task.setResponsibleExecutor(responsibleExecutor.get(random.nextInt(responsibleExecutor.size())));
            task.setSignOfControl(signOfControl.get(random.nextInt(signOfControl.size())));
            task.setOrderController(orderController.get(random.nextInt(orderController.size())));
            return task;
        } catch (DocumentExistsException e) {
            e.printStackTrace();
        }
        return null;
    }
}
