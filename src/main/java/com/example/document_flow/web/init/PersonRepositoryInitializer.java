package com.example.document_flow.web.init;

import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.repository.absraction.staff.PersonRepository;
import com.example.document_flow.repository.implement.staff.PersonRepositoryImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Отвечает за инициализацию и заполнения <code>PersonRepository</code> данными при запуске приложения
 *
 * @author Баратов Руслан
 */
@WebListener
public class PersonRepositoryInitializer implements ServletContextListener {

    private final PersonRepository PERSON_REPOSITORY = PersonRepositoryImpl.getInstance();

    private final DataGenerator DATA_GENERATOR = DataGenerator.getInstance();

    /**
     * Заполняет <code>PersonRepository</code> данными при запуске приложения
     *
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        PERSON_REPOSITORY.saveAll(DATA_GENERATOR.PERSON_LIST.stream().limit(100).toList());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
