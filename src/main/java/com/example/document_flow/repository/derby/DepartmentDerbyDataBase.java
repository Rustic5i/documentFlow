package com.example.document_flow.repository.derby;

import com.example.document_flow.entity.staff.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDerbyDataBase {

    private Connection connection;

    private Statement statement;

    private PreparedStatement preparedStatement;

    private PersonDerbyDataBase personDerbyDataBase = PersonDerbyDataBase.getInstance();

    private static DepartmentDerbyDataBase derbyDataBase;

    private DepartmentDerbyDataBase() {
        connectToDB();
        // createDepartmentTable();
    }

    private void connectToDB() {
        try {
            connection = SessionDerbyDataBase.connectToDB();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createDepartmentTable() {
        try {
            statement.executeUpdate("create table DEPARTMENT\n" +
                    "(\n" +
                    "\tID int not null\n" +
                    "\t\tconstraint DEPARTMENT_PK\n" +
                    "\t\t\tprimary key,\n" +
                    "\tFULL_NAME VARCHAR(25),\n" +
                    "\tSHORT_NAME VARCHAR(25),\n" +
                    "\tMANAGER_ID int\n" +
                    "\t\tconstraint MANAGER_ID\n" +
                    "\t\t\treferences PERSON,\n" +
                    "\tCONTACT_PHONE_NUMBER VARCHAR(25)\n" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveDepartment(Department department) {
        try {
            preparedStatement = connection
                    .prepareStatement("INSERT INTO APP.DEPARTMENT (ID, FULL_NAME, SHORT_NAME, MANAGER_ID, CONTACT_PHONE_NUMBER)\n" +
                            "VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, (int) department.getId());
            preparedStatement.setString(2, department.getFullName());
            preparedStatement.setString(3, department.getShortName());
            preparedStatement.setInt(4, (int) department.getManager().getId());
            preparedStatement.setString(5, department.getContactPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Department> getAllDepartment() {
        List<Department> departmentList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM DEPARTMENT");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                departmentList.add(new Department().newBuilder()
                        .setId(rs.getInt(1))
                        .setFullName(rs.getString(2))
                        .setShortName(rs.getString(3))
                        .setManager(personDerbyDataBase.getPersonById(rs.getInt(4)))
                        .setContactPhoneNumber(rs.getString(5))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentList;
    }

    public void saveAllDepartment(List<Department> departmentList) {
        try {
            connection.setAutoCommit(false);
            departmentList.stream().forEach(this::saveDepartment);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
