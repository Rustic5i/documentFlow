package com.example.document_flow.repository.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SessionDerbyDataBase {

    private static Connection connection;

    private static final String DATABASE_PATH = "jdbc:derby://localhost:1527/mydb;create=true";

    private SessionDerbyDataBase() {
        connectToDB();
    }

    public static Connection connectToDB() {
        try {
            connection = DriverManager.getConnection(DATABASE_PATH);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
