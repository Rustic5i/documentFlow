package com.example.document_flow.repository.derby;

import com.example.document_flow.entity.staff.Person;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDerbyDataBase {

    private Connection connection;

    private Statement statement;

    private PreparedStatement preparedStatement;

    private static PersonDerbyDataBase derbyDataBase;

    private PersonDerbyDataBase() {
        connectToDB();
        createPersonTable();
    }

    private void connectToDB() {
        try {
            connection = SessionDerbyDataBase.connectToDB();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createPersonTable() {
        try {
            statement.executeUpdate("create table PERSON\n" +
                    "(\n" +
                    "\tID int generated always as identity\n" +
                    "\t\tconstraint TABLE_NAME_PK\n" +
                    "\t\t\tprimary key,\n" +
                    "\tSURNAME VARCHAR(25),\n" +
                    "\tNAME VARCHAR(25),\n" +
                    "\tPATRONYMIC VARCHAR(25),\n" +
                    "\tPOST VARCHAR(100),\n" +
                    "\tDATA_OF_BIRTH DATE,\n" +
                    "\tPHONE_NUMBER int\n" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePerson(Person person) {
        try {
            preparedStatement = connection
                    .prepareStatement("INSERT INTO APP.PERSON (SURNAME, NAME, PATRONYMIC, POST, DATA_OF_BIRTH, PHONE_NUMBER)\n" +
                            "VALUES (?, ?, ?, ?, ?, ?)");
//            preparedStatement.setInt(1, (int) person.getId());
            preparedStatement.setString(1, person.getSurname());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getPatronymic());
            preparedStatement.setString(4, person.getPost());
            preparedStatement.setDate(5, new Date(person.getDateOfBirth().getTime()));
            preparedStatement.setInt(6, person.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAllPerson() {
        List<Person> personList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM PERSON");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                personList.add(new Person().newBuilder()
                        .setId(rs.getInt(1))
                        .setSurname(rs.getString(2))
                        .setName(rs.getString(3))
                        .setPatronymic(rs.getString(4))
                        .setPost(rs.getString(5))
                        .setDateOfBirth(rs.getDate(6))
                        .setPhoneNumber(rs.getInt(7)).build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public void saveAllPerson(List<Person> personList) {
        try {
            connection.setAutoCommit(false);
            personList.stream().forEach(this::savePerson);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return синголтон обьект
     */
    public static PersonDerbyDataBase getInstance() {
        if (derbyDataBase == null) {
            derbyDataBase = new PersonDerbyDataBase();
        }
        return derbyDataBase;
    }
}
