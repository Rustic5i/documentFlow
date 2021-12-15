package com.example.document_flow.config.DataBase.implement;

import com.example.document_flow.config.DataBase.abstraction.SessionManager;
import com.example.document_flow.config.ReadFileProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Конфигурационный класс для настройки соединение к базе данных Derby
 *
 * @author Баратов Руслан
 */
public class SessionManagerIml implements SessionManager {

    private static SessionManagerIml sessionManager;

    private final HikariDataSource DATA_SOURCE;

    private final HikariConfig CONFIG = new HikariConfig();

    private final ReadFileProperties PROPERTIES = new ReadFileProperties();

    {
        CONFIG.setJdbcUrl(PROPERTIES.getProperty("derby.datasource.url"));
        CONFIG.setDriverClassName(PROPERTIES.getProperty("db.driver"));
        DATA_SOURCE = new HikariDataSource(CONFIG);
    }

    private SessionManagerIml() {
    }

    /**
     * @return синголтон обьект
     */
    public static synchronized SessionManagerIml getInstance() {
        if (sessionManager == null) {
            sessionManager = new SessionManagerIml();
        }
        return sessionManager;
    }

    /**
     * @return соединение (сеанс) с определенной базой данных.
     */
    public Connection getConnection() throws SQLException {
        return DATA_SOURCE.getConnection();
    }
}
