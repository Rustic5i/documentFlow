package com.example.document_flow.repository.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * тут будет id
 */
public class StaffDerbyDataBase {

    private static Connection connection;

    private static Statement statement;

    private static final String DATABASE_PATH = "jdbc:derby://localhost:1527/mydb;create=true";

    private static StaffDerbyDataBase staffDataBase;

    private StaffDerbyDataBase() {
        connectToDB();
    }

    private static void connectToDB() {
        try (Connection connection = DriverManager.getConnection(DATABASE_PATH)) {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTableStaff(){

    }

    /**
     * @return синголтон обьект
     */
    public static StaffDerbyDataBase getInstance() {
        if (staffDataBase == null) {
            staffDataBase = new StaffDerbyDataBase();
        }
        return staffDataBase;
    }
}
