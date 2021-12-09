package com.example.document_flow.web.observer;

import com.example.document_flow.DAO.abstraction.TableCreator;
import com.example.document_flow.DAO.implement.derby.table.implement.StaffDerbyTableCreator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Класс инициатор создания таблиц в базе банных Derby
 *
 * @author Баратов Руслан
 */
@WebListener
public class CreatingTablesObserver implements ServletContextListener {

    private final TableCreator CREATOR = StaffDerbyTableCreator.getInstance();

    /**
     * Создает таблицы в бд при запуске программы
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        CREATOR.creatTablesDB();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
