package com.example.document_flow.repository.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DepartmentDerbyDataBase {

    private static Connection connection;

    private static Statement statement;

    private static final String DATABASE_PATH = "jdbc:derby://localhost:1527/mydb;create=true";

    private static DepartmentDerbyDataBase derbyDataBase;

    private DepartmentDerbyDataBase() {
        connectToDB();
    }

    private static void connectToDB() {
        try (Connection connection = DriverManager.getConnection(DATABASE_PATH)) {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTableDepartment(){

    }

    /**
     * @return синголтон обьект
     */
    public static DepartmentDerbyDataBase getInstance() {
        if (derbyDataBase == null) {
            derbyDataBase = new DepartmentDerbyDataBase();
        }
        return derbyDataBase;
    }
}
