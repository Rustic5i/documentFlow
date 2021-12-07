package com.example.document_flow.repository.implement.derby.initializers;

import com.example.document_flow.repository.implement.derby.PersonDerbyDataBase;
import com.example.document_flow.repository.implement.derby.SessionDerbyDataBase;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
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
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"import.sql";
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
      //  connectToDB();
//        PersonDerbyDataBase personDerbyDataBase = PersonDerbyDataBase.getInstance();
        readSqlFile();
    //    readPropertied();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private static void readPropertied() {
        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            String configPath = rootPath + "config/config.properties";

            Properties properties = new Properties();
            properties.load(new FileInputStream(configPath));

            String sourcesDataBase = properties.getProperty("derby.datasource.url");
            System.out.println(sourcesDataBase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
