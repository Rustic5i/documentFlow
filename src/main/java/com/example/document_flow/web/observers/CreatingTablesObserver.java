package com.example.document_flow.web.observers;

import com.example.document_flow.DAO.implement.table.TableCreator;
import com.example.document_flow.exception.CreateTableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Класс инициатор создания таблиц в базе банных Derby
 *
 * @author Баратов Руслан
 */
public class CreatingTablesObserver implements ServletContextListener {

    private final com.example.document_flow.DAO.abstraction.TableCreator creator = TableCreator.getInstance();

    private final Logger logger = LoggerFactory.getLogger(CreatingTablesObserver.class.getName());

    /**
     * Создает таблицы в бд при запуске программы
     *
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            creator.createTablesDB();
        } catch (CreateTableException e) {
            logger.error("Ошибка при создание таблицы в бд" + e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
