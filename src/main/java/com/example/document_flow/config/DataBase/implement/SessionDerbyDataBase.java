package com.example.document_flow.config.DataBase.implement;

import com.example.document_flow.config.DataBase.abstraction.SessionDataBase;
import com.example.document_flow.config.ReadFileProperties;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SessionDerbyDataBase implements SessionDataBase {

    private final ReadFileProperties PROPERTIES = new ReadFileProperties();

    private Connection connection;

    private final String CONFIG_PATH = "config/config.properties";

    private static SessionDerbyDataBase sessionDerbyDataBase;

    private SessionDerbyDataBase() {
        connectToDB();
    }

    private synchronized void connectToDB() {
        PROPERTIES.load(CONFIG_PATH);
        try {
            Class.forName(PROPERTIES.getProperty("db.driver"));
            connection = DriverManager.getConnection(PROPERTIES.getProperty("derby.datasource.url"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return синголтон обьект
     */
    public static SessionDerbyDataBase getInstance() {
        if (sessionDerbyDataBase == null) {
            sessionDerbyDataBase = new SessionDerbyDataBase();
        }
        return sessionDerbyDataBase;
    }
}
