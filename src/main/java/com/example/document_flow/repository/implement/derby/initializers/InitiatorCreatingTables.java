package com.example.document_flow.repository.implement.derby.initializers;

import com.example.document_flow.repository.implement.derby.PersonDerbyDataBase;
import com.example.document_flow.repository.implement.derby.SessionDerbyDataBase;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

@WebListener
public class InitiatorCreatingTables implements ServletContextListener {

    private Connection connection;

    private final SessionDerbyDataBase SESSION_DERBY_DATA_BASE = SessionDerbyDataBase.getInstance();

    private Statement statement;

    private void connectToDB() {
        try {
            connection = SESSION_DERBY_DATA_BASE.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void readSqlFile(){
        File file = new File("classes\\import.sql");
        String path = file.getAbsolutePath();
        String delimiter = ";";

        try {
            Scanner scanner = new Scanner(new File(path)).useDelimiter(delimiter);

            while (scanner.hasNext()){
               String sqlScript = scanner.next()+delimiter;
               statement.executeUpdate(sqlScript);
            }
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        connectToDB();
//        PersonDerbyDataBase personDerbyDataBase = PersonDerbyDataBase.getInstance();
        readSqlFile();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
