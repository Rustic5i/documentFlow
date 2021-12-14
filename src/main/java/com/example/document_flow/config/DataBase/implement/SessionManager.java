package com.example.document_flow.config.DataBase.implement;

import com.example.document_flow.config.DataBase.abstraction.SessionDataBase;
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
public class SessionManager implements SessionDataBase {

    private static SessionManager sessionDerbyDataBase;

    private final HikariDataSource DATA_SOURCE;

    private final HikariConfig CONFIG = new HikariConfig();

    private final ReadFileProperties PROPERTIES = new ReadFileProperties();

    {
        CONFIG.setJdbcUrl(PROPERTIES.getProperty("derby.datasource.url"));
        CONFIG.setDriverClassName(PROPERTIES.getProperty("db.driver"));
        DATA_SOURCE = new HikariDataSource(CONFIG);
    }

    private SessionManager() {
    }

    /**
     * @return синголтон обьект
     */
    public static synchronized SessionManager getInstance() {
        if (sessionDerbyDataBase == null) {
            sessionDerbyDataBase = new SessionManager();
        }
        return sessionDerbyDataBase;
    }

    /**
     * @return соединение (сеанс) с определенной базой данных.
     */
    public Connection getConnection() throws SQLException {
        return DATA_SOURCE.getConnection();
    }
}
