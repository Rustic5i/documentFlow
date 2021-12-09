package com.example.document_flow.config.DataBase.implement;

import com.example.document_flow.config.DataBase.abstraction.SessionDataBase;
import com.example.document_flow.util.read.ReadFileProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Конфигурационный класс для настройки соединение к базе данных Derby
 *
 * @author Баратов Руслан
 */
public class SessionDerbyDataBase implements SessionDataBase, AutoCloseable {

    private final ReadFileProperties PROPERTIES = new ReadFileProperties();

    private Connection connection;

    private final String CONFIG_PATH = "config/config.properties";

    private static SessionDerbyDataBase sessionDerbyDataBase;

    private final Logger LOGGER = LoggerFactory.getLogger(SessionDerbyDataBase.class.getName());

    private SessionDerbyDataBase() {
    }

//    /**
//     * Подключение к базе данных Derby
//     */
//    private synchronized void connectToDB() {
//        PROPERTIES.load(CONFIG_PATH);
//        try {
//            Class.forName(PROPERTIES.getProperty("db.driver"));
//            connection = DriverManager.getConnection(PROPERTIES.getProperty("derby.datasource.url"));
//        } catch (SQLException | ClassNotFoundException e) {
//            LOGGER.error("Ошибка подключение к базе данных Derby", e);
//        }
//    }

    /**
     * @return соединение (сеанс) с определенной базой данных.
     */
    @Override
    public Connection getConnection() {
        try {
            if (connection == null || !(connection.isValid(0))) {
                PROPERTIES.load(CONFIG_PATH);
                try {
                    Class.forName(PROPERTIES.getProperty("db.driver"));
                    connection = DriverManager.getConnection(PROPERTIES.getProperty("derby.datasource.url"));
                } catch (SQLException | ClassNotFoundException e) {
                    LOGGER.error("Ошибка подключение к базе данных Derby", e);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Тайм-аут указан меньше 0", e);
        }
        return connection;
    }

    /**
     * Закрывает соединение(сеанс) к базе данных
     *
     * @throws SQLException если возникает ошибка доступа к базе данных
     */
    @Override
    public void close() throws SQLException {
        connection.close();
        System.out.println("Я ЗАКРЫЛ СОЕДИНЕНИЕ С БАЗАОЙ ДАННОЙ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    /**
     * @return синголтон обьект
     */
    public synchronized static SessionDerbyDataBase getInstance() {
        if (sessionDerbyDataBase == null) {
            sessionDerbyDataBase = new SessionDerbyDataBase();
        }
        return sessionDerbyDataBase;
    }
}
