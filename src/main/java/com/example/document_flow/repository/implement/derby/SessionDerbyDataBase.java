package com.example.document_flow.repository.implement.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SessionDerbyDataBase {

    private Properties properties = new Properties();

    private Connection connection;

    private final String DATABASE_PATH = "jdbc:derby:C:\\Program Files\\Derby\\derby-10.13.1.1\\db-derby-10.13.1.1-bin\\mydb1;create=true";

    private String DRIVER_DATA_BASE = "org.apache.derby.jdbc.EmbeddedDriver";

    private static SessionDerbyDataBase sessionDerbyDataBase;

    private SessionDerbyDataBase() {
        connectToDB();
    }

    private Connection connectToDB() {
        try {
            Class.forName(DRIVER_DATA_BASE);
            connection = DriverManager.getConnection(DATABASE_PATH);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Connection getConnection() {
        return connection;
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

    private void readPropertied(){
        String derbyDatasourceUrl = properties.getProperty("derby.datasource.url");
    }
}
