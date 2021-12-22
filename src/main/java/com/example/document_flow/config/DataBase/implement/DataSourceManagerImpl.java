package com.example.document_flow.config.DataBase.implement;

import com.example.document_flow.config.DataBase.abstraction.DataSourceManager;
import com.example.document_flow.config.ReadFileProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * Конфигурационный класс для настройки соединение c базой данных Derby
 *
 * @author Баратов Руслан
 */
public class DataSourceManagerImpl implements DataSourceManager {

    private static final String PROPERTIES_KEY_URL = "derby.datasource.url";

    private static final String PROPERTIES_KEY_DRIVER = "db.driver";

    private static DataSourceManagerImpl sessionManager;

    private final HikariConfig config = new HikariConfig();

    private final ReadFileProperties properties = new ReadFileProperties();

    private DataSource dataSource;

    {
        config.setJdbcUrl(properties.getProperty(PROPERTIES_KEY_URL));
        config.setDriverClassName(properties.getProperty(PROPERTIES_KEY_DRIVER));
    }

    private DataSourceManagerImpl() {
    }

    /**
     * Инициализирует объект реализующий интерфейс {@link DataSource}
     *
     * @return инициализируемый объект реализующий интерфейс {@link DataSource}
     */
    private DataSource initDataSource() {
        if (dataSource == null) {
            dataSource = new HikariDataSource(config);
        }
        return dataSource;
    }

    /**
     * @return синголтон обьект
     */
    public static synchronized DataSourceManagerImpl getInstance() {
        if (sessionManager == null) {
            sessionManager = new DataSourceManagerImpl();
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
