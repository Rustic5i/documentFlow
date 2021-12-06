package com.example.document_flow.repository.implement.derby;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.repository.absraction.dao.PersonDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO слой отвечает за предоставления доступа к базе данных.
 * Содержит методы связанные с сущностью <code>Person</code>
 *
 * @author Баратов Руслан
 */
public class PersonDerbyDataBase implements PersonDAO {

    private Connection connection;

    private Statement statement;

    private PreparedStatement preparedStatement;

    private static PersonDerbyDataBase derbyDataBase;

    private PersonDerbyDataBase() {
        connectToDB();
        //createPersonTable();
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
            statement.executeUpdate("create table person\n" +
                    "(\n" +
                    "\tid int not null\n" +
                    "\t\tconstraint PERSON_PK\n" +
                    "\t\t\tprimary key,\n" +
                    "\tsurname varchar(25),\n" +
                    "\tname varchar(25),\n" +
                    "\tpatronymic varchar(25),\n" +
                    "\tpost varchar(100),\n" +
                    "\tdata_of_birth date,\n" +
                    "\tphone_number int\n" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сохранить объект класса <code>Person</code>
     *
     * @param person объект класса <code>Person</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void savePerson(Person person) throws SaveObjectException {
        try {
            preparedStatement = connection
                    .prepareStatement("INSERT INTO APP.PERSON (ID, SURNAME, NAME, PATRONYMIC, POST, DATA_OF_BIRTH, PHONE_NUMBER)\n" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, (int) person.getId());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setString(3, person.getName());
            preparedStatement.setString(4, person.getPatronymic());
            preparedStatement.setString(5, person.getPost());
            preparedStatement.setDate(6, new Date(person.getDateOfBirth().getTime()));
            preparedStatement.setInt(7, person.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SaveObjectException("Person с id " + person.getId() + " уже существует " + e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получить список всех сохраненных объектов класса <code>Person</code>
     *
     * @return список сохраненных объектов класса <code>Person</code>
     */
    @Override
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

    /**
     * Сохранить список объектов класса <code>Person</code>
     *
     * @param personList список объектов класса <code>Person</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void saveAllPerson(List<Person> personList) throws SaveObjectException {
        try {
            connection.setAutoCommit(false);
            for (Person person : personList) {
                savePerson(person);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Найти объект класса <code>Person</code> по id
     *
     * @param id id объекта класса <code>Person</code>
     * @return найденный объект класса <code>Person</code>
     */
    @Override
    public Optional<Person> findPersonById(long id) {
        Person person = new Person();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID=?");
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                person.newBuilder()
                        .setId(rs.getInt(1))
                        .setSurname(rs.getString(2))
                        .setName(rs.getString(3))
                        .setPatronymic(rs.getString(4))
                        .setPost(rs.getString(5))
                        .setDateOfBirth(rs.getDate(6))
                        .setPhoneNumber(rs.getInt(7))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(person);
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
