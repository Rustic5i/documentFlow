package com.example.document_flow.config.DataBase.implement;

import com.example.document_flow.config.DataBase.abstraction.SessionManager;
import com.example.document_flow.config.ReadFileProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * Конфигурационный класс для настройки соединение c базой данных Derby
 *
 * @author Баратов Руслан
 */
public class SessionManagerImp implements SessionManager {

    private static final String PROPERTIES_KEY_URL = "derby.datasource.url";

    private static final String PROPERTIES_KEY_DRIVER = "db.driver";

    private static SessionManagerImp sessionManager;

    private final HikariDataSource DATA_SOURCE;

    private final HikariConfig CONFIG = new HikariConfig();

    private final ReadFileProperties PROPERTIES = new ReadFileProperties();

    {
        CONFIG.setJdbcUrl(PROPERTIES.getProperty(PROPERTIES_KEY_URL));
        CONFIG.setDriverClassName(PROPERTIES.getProperty(PROPERTIES_KEY_DRIVER));
        DATA_SOURCE = new HikariDataSource(CONFIG);
    }

    private SessionManagerImp() {
    }

    /**
     * @return синголтон обьект
     */
    public static synchronized SessionManagerImp getInstance() {
        if (sessionManager == null) {
            sessionManager = new SessionManagerImp();
        }
        return sessionManager;
    }

    /**
     * @return Объект, реализующий интерфейс источника данных. Объект источника данных,
     * являющийся альтернативой средству DriverManager,
     * является предпочтительным средством установления соединения.
     */
    public DataSource getDataSource() {
        return DATA_SOURCE;
    }
}
