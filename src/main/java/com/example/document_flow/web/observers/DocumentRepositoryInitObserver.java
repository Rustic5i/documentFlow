package com.example.document_flow.web.observers;

import com.example.document_flow.repository.absraction.document.DocumentRepository;
import com.example.document_flow.repository.implement.document.DocumentRepositoryImpl;
import com.example.document_flow.generator.DocumentGenerator;

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

    /**
     * Заполняет <code>DocumentRepository</code> данными при запуске приложения
     *
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DOCUMENT_REPOSITORY.saveAll(DocumentGenerator.run(100));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
