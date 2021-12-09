package com.example.document_flow.web.observer;

import com.example.document_flow.config.DataBase.abstraction.SessionDataBase;
import com.example.document_flow.config.DataBase.implement.SessionDerbyDataBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

/**
 * Класс отвечает за закрытие соединения с базой данных при завершении программы
 *
 * @author Баратов Руслан
 */
@WebListener
public class CloseSessionDataBaseObserver implements ServletContextListener {

    private final SessionDataBase SESSION_DATA_BASE = SessionDerbyDataBase.getInstance();

    private final Logger LOGGER = LoggerFactory.getLogger(CloseSessionDataBaseObserver.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    }

    /**
     * Закрывает соединение с базой данных при завершении программы
     *
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            SESSION_DATA_BASE.close();
        } catch (SQLException e) {
            LOGGER.error("Ошибка закрытия соединения с базой данных", e);
        }
    }
}
