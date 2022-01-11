package com.example.document_flow.web.observers;

import com.example.document_flow.entity.document.Incoming;
import com.example.document_flow.entity.document.Task;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.factory.document.IncomingDocumentFactory;
import com.example.document_flow.factory.document.TaskDocumentFactory;
import com.example.document_flow.service.abstraction.document.IncomingService;
import com.example.document_flow.service.abstraction.document.OutgoingService;
import com.example.document_flow.service.implement.document.derby.IncomingServiceDerby;
import com.example.document_flow.service.implement.document.derby.OutgoingServiceDerby;
import com.example.document_flow.service.implement.document.derby.TaskServiceDerby;
import com.example.document_flow.service.implement.staff.derby.PersonServiceDerby;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

public class DocumentDataImportObserver implements ServletContextListener {

    private final TaskServiceDerby taskServiceDerby = TaskServiceDerby.getInstance();

    private final IncomingService incomingService = IncomingServiceDerby.getInstance();

    private final OutgoingService outgoingService = OutgoingServiceDerby.getInstance();

    private final PersonServiceDerby personServiceDerby = PersonServiceDerby.getInstance();

    private List<Person> personList;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        personList = getAllPerson();
        createDataTaskDB();
        createDataIncomingDB();
    }

    private void createDataIncomingDB() {
        for (int i = 0; i < 4; i++) {
            try {
                Incoming incoming = new IncomingDocumentFactory().create();
                incoming.setAuthor(personList.get(i));
                incoming.setSource(personList.get(i + 4));
                incomingService.save(incoming);
            } catch (SaveObjectException e) {
                e.printStackTrace();
            }
        }
    }

    private void createDataTaskDB() {
        for (int i = 0; i < 4; i++) {
            try {
                Task task = new TaskDocumentFactory().create();
                task.setAuthor(personList.get(i));
                task.setOrderController(personList.get(i + 4));
                task.setResponsibleExecutor(personList.get(i + 5));
                taskServiceDerby.save(task);
            } catch (SaveObjectException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Person> getAllPerson() {
        try {
            return personServiceDerby.getAll();
        } catch (GetDataObjectException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
