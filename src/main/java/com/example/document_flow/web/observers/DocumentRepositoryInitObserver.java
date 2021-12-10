package com.example.document_flow.web.observers;

import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.repository.absraction.document.DocumentRepository;
import com.example.document_flow.repository.implement.document.DocumentRepositoryImpl;
import com.example.document_flow.generator.DocumentGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Отвечает за инициализацию и заполнения <code>DocumentRepository</code> данными при запуске приложения
 *
 * @author Баратов Руслан
 */
@WebListener
public class DocumentRepositoryInitObserver implements ServletContextListener {

    private final DocumentRepository DOCUMENT_REPOSITORY = DocumentRepositoryImpl.getInstance();

    private final Logger LOGGER = LoggerFactory.getLogger(DocumentRepositoryInitObserver.class.getName());

    /**
     * Заполняет <code>DocumentRepository</code> данными при запуске приложения
     *
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            DOCUMENT_REPOSITORY.saveAll(DocumentGenerator.run(100));
        } catch (SaveObjectException e) {
            LOGGER.warn("",e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
