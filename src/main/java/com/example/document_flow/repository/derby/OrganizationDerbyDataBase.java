package com.example.document_flow.repository.derby;

import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.entity.staff.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrganizationDerbyDataBase {

    private Connection connection;

    private Statement statement;

    private PreparedStatement preparedStatement;

    private static OrganizationDerbyDataBase derbyDataBase;

    private OrganizationDerbyDataBase() {
        connectToDB();
         createOrganizationTable();
    }

    private void connectToDB() {
        try {
            connection = SessionDerbyDataBase.connectToDB();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createOrganizationTable() {
        try {
            statement.executeUpdate("create table organization\n" +
                    "(\n" +
                    "\tid int not null\n" +
                    "\t\tconstraint ORGANIZATION_PK\n" +
                    "\t\t\tprimary key,\n" +
                    "\tfull_name varchar(25),\n" +
                    "\tshort_name varchar(25),\n" +
                    "\tmanager_id int\n" +
                    "\t\tconstraint manager\n" +
                    "\t\t\treferences PERSON,\n" +
                    "\tcontact_phone_number varchar(25)\n" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveOrganization(Organization organization) {
        try {

            preparedStatement = connection
                    .prepareStatement("INSERT INTO ORGANIZATION (FULL_NAME, SHORT_NAME, MANAGER_ID, CONTACT_PHONE_NUMBER) VALUE (?,?,?,?,?,)");
            preparedStatement.setString(1, organization.getFullName());
            preparedStatement.setString(2, organization.getShortName());
            preparedStatement.setInt(3, (int) organization.getManager().getId());
            preparedStatement.setString(4, organization.getContactPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAllOrganization() {
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

    public void saveAllOrganization(List<Person> personList) {
//        try {
//            connection.setAutoCommit(false);
//            personList.stream().forEach(this::saveOrganization);
//            connection.commit();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * @return синголтон обьект
     */
    public static OrganizationDerbyDataBase getInstance() {
        if (derbyDataBase == null) {
            derbyDataBase = new OrganizationDerbyDataBase();
        }
        return derbyDataBase;
    }
}
