package com.example.document_flow.web.observers;

import com.example.document_flow.DAO.abstraction.TableCreator;
import com.example.document_flow.DAO.implement.table.implement.StaffTableCreator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Класс инициатор создания таблиц в базе банных Derby
 *
 * @author Баратов Руслан
 */
public class CreatingTablesObserver implements ServletContextListener {

    private final TableCreator CREATOR = StaffTableCreator.getInstance();

    /**
     * Создает таблицы в бд при запуске программы
     *
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
