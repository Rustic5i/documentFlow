package com.example.document_flow.web.init;

import com.example.document_flow.Main;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Отвечает за инициализацию обетов при старте приложения
 *
 * @author Баратов Руслан
 */
@WebListener
public class ObjectInitializer implements ServletContextListener {

    /**
     *Инициализирует обьект при старте приложения
     *
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String[] arg = {"100"};
        Main.main(arg);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
