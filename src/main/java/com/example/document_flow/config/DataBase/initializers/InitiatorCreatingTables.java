package com.example.document_flow.config.DataBase.initializers;

import com.example.document_flow.config.DataBase.implement.SessionDerbyDataBase;
import com.example.document_flow.config.ReadFileSql;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@WebListener
public class InitiatorCreatingTables implements ServletContextListener {

    private Connection connection;

    private final SessionDerbyDataBase SESSION_DERBY_DATA_BASE = SessionDerbyDataBase.getInstance();

    private final ReadFileSql READ_FILE_SQL = new ReadFileSql();

    private Statement statement;

    private void connectToDB() {
        try {
            this.connection = SESSION_DERBY_DATA_BASE.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void creatTableDB() {
        List<String> arraySqlScript = READ_FILE_SQL.read("import.sql");

        for (String sqlScript : arraySqlScript) {
            try {
                statement.executeUpdate(sqlScript);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        connectToDB();
        creatTableDB();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        SESSION_DERBY_DATA_BASE.close();
    }
}
