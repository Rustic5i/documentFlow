package com.example.document_flow.web.init;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.generator.DocumentGenerator;
import com.example.document_flow.repository.DAO.DAO;
import com.example.document_flow.repository.DAO.DocumentDAO;
import com.example.document_flow.repository.document.DocumentRepository;
import com.example.document_flow.repository.staff.StaffRepositoryXml;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Отвечает за инициализацию и заполнения репозитории данными при запуске приложения
 *
 * @author Баратов Руслан
 */
@WebListener
public class ObjectInitializer implements ServletContextListener {

    private final DocumentDAO DOCUMENT_REPOSITORY = DocumentRepository.getInstance();

    private final DAO<Person> PERSON_REPOSITORY = new StaffRepositoryXml<>(Person.class);

    private final DataGenerator DATA_GENERATOR = DataGenerator.getInstance();

    /**
     * Заполняет репозиторий данными при запуске приложения
     *
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DOCUMENT_REPOSITORY.saveAll(DocumentGenerator.run(100));
        PERSON_REPOSITORY.saveAll(DATA_GENERATOR.PERSON_LIST.stream().limit(100).toList());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
