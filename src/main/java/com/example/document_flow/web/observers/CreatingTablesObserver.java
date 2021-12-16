package com.example.document_flow.web.observers;

import com.example.document_flow.DAO.abstraction.TableCreator;
import com.example.document_flow.DAO.implement.table.implement.StaffTableCreator;
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

    private final TableCreator CREATOR = StaffTableCreator.getInstance();

    private final Logger LOGGER = LoggerFactory.getLogger(CreatingTablesObserver.class.getName());

    /**
     * Создает таблицы в бд при запуске программы
     *
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            CREATOR.createTablesDB();
        } catch (CreateTableException e) {
            LOGGER.error("Ошибка при создание таблицы в бд" + e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
