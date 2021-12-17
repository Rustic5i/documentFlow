package com.example.document_flow.config.DataBase.implement;

import com.example.document_flow.config.DataBase.abstraction.ManagerDataSource;
import com.example.document_flow.config.ReadFileProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * Конфигурационный класс для настройки соединение c базой данных Derby
 *
 * @author Баратов Руслан
 */
public class ManagerDataSourceImp implements ManagerDataSource {

    private static final String PROPERTIES_KEY_URL = "derby.datasource.url";

    private static final String PROPERTIES_KEY_DRIVER = "db.driver";

    private static ManagerDataSourceImp sessionManager;

    private final HikariConfig CONFIG = new HikariConfig();

    private final ReadFileProperties PROPERTIES = new ReadFileProperties();

    private DataSource DATA_SOURCE;

    {
        CONFIG.setJdbcUrl(PROPERTIES.getProperty(PROPERTIES_KEY_URL));
        CONFIG.setDriverClassName(PROPERTIES.getProperty(PROPERTIES_KEY_DRIVER));
    }

    private ManagerDataSourceImp() {
    }

    /**
     * Инициализирует объект реализующий интерфейс {@link DataSource}
     *
     * @return инициализируемый объект реализующий интерфейс {@link DataSource}
     */
    private DataSource initDataSource() {
        if (DATA_SOURCE == null) {
            DATA_SOURCE = new HikariDataSource(CONFIG);
        }
        return DATA_SOURCE;
    }

    /**
     * @return синголтон обьект
     */
    public static synchronized ManagerDataSourceImp getInstance() {
        if (sessionManager == null) {
            sessionManager = new ManagerDataSourceImp();
        }
        return sessionManager;
    }

    /**
     * @return Объект, реализующий интерфейс источника данных. Объект источника данных,
     * являющийся альтернативой средству DriverManager,
     * является предпочтительным средством установления соединения.
     */
    public DataSource getDataSource() {
        return initDataSource();
    }
}
