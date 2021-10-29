package com.example.document_flow.factory.generateDate;

import com.example.document_flow.model.person.Person;
import java.util.*;

/**
 * Класс для хранения и предоставления рандомных данных для документа {@link com.example.document_flow.model.Task}
 */
public class GenerateDataTask {

    private final Long DAY = 86400000L;

    private Random random = new Random();

    private Date dateOfIssue; //дата выдачи поручения

    private Date termOfExecution; //срок исполнения поручения

    private List<Person> responsibleExecutor; //ответственный исполнитель

    private List<String> signOfControl;  //признак контрольности

    private List<String> orderController;

    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        dateOfIssue = calendar.getTime();

        termOfExecution = new Date();

        responsibleExecutor = GeneratePersons.names;

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

    /**
     * Генерирует рандомный "срок исполнения"
     * @return рандомную Date
     */
    public Date getTermOfExecution() {
        termOfExecution.setTime(dateOfIssue.getTime() + DAY * random.nextLong(10));
        return termOfExecution;
    }

    /**
     * Генерирует рандомный "дата выдачи поручения"
     * @return рандомную Date
     */
    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    /**
     *
     * @return Возвращает рандомного исполнителя
     */
    public Person getResponsibleExecutor() {
        return responsibleExecutor.get(random.nextInt(responsibleExecutor.size()));
    }

    public String getSignOfControl() {
        return signOfControl.get(random.nextInt(signOfControl.size()));
    }

    public String getOrderController() {
        return orderController.get(random.nextInt(orderController.size()));
    }
}
