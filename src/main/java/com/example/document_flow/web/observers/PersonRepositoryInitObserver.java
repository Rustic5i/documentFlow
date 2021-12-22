package com.example.document_flow.web.observers;

import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.repository.absraction.staff.PersonRepository;
import com.example.document_flow.repository.implement.staff.PersonRepositoryXmlImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Отвечает за инициализацию и заполнения <code>PersonRepository</code> данными при запуске приложения
 *
 * @author Баратов Руслан
 */
@WebListener
public class PersonRepositoryInitObserver implements ServletContextListener {

    private final PersonRepository personRepository = PersonRepositoryXmlImpl.getInstance();

    private final DataGenerator dataGenerator = DataGenerator.getInstance();

    private final Logger logger = LoggerFactory.getLogger(PersonRepositoryInitObserver.class.getName());

    /**
     * Заполняет <code>PersonRepository</code> данными при запуске приложения
     *
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            personRepository.saveAll(dataGenerator.PERSON_LIST.stream().limit(100).toList());
        } catch (SaveObjectException e) {
            logger.warn("",e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
