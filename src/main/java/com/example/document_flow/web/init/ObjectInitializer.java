package com.example.document_flow.web.init;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.generator.DocumentGenerator;
import com.example.document_flow.service.abstraction.AbstractDocumentService;
import com.example.document_flow.service.abstraction.AbstractService;
import com.example.document_flow.service.implement.DocumentService;
import com.example.document_flow.service.implement.PersonService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Отвечает за инициализацию объектов при старте приложения
 *
 * @author Баратов Руслан
 */
@WebListener
public class ObjectInitializer implements ServletContextListener {

    private final AbstractDocumentService DOCUMENT_SERVICE = DocumentService.getInstance();

    private final AbstractService<Person> PERSON_SERVICE = PersonService.getInstance();

    private final DataGenerator DATA_GENERATOR = DataGenerator.getInstance();

    /**
     * Инициализирует объект при старте приложения
     *
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DOCUMENT_SERVICE.saveAll(DocumentGenerator.run(100));
        PERSON_SERVICE.saveAll(DATA_GENERATOR.PERSON_LIST.stream().limit(100).toList());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
