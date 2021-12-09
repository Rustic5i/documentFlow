package com.example.document_flow.DAO.implement.derby;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.config.DataBase.abstraction.SessionDataBase;
import com.example.document_flow.config.DataBase.implement.SessionDerbyDataBase;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.SaveObjectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * DAO слой отвечает за предоставления доступа к базе данных.
 * Содержит методы связанные с сущностью <code>Person</code>
 *
 * @author Баратов Руслан
 */
public class PersonDerbyDataBase implements DAOCrud<Person> {

    private final SessionDataBase SESSION_DERBY_DATA_BASE = SessionDerbyDataBase.getInstance();

    private final Logger LOGGER = LoggerFactory.getLogger(PersonDerbyDataBase.class.getName());

    private static PersonDerbyDataBase derbyDataBase;

    private PersonDerbyDataBase() {
    }

    /**
     * Удалить по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        try (Connection connection = SESSION_DERBY_DATA_BASE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM APP.PERSON WHERE ID = ?")) {
            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteObjectException("Ошибка удаление Person c id " + id);
        }
    }

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(Person object) throws SaveObjectException {
        try (Connection connection = SESSION_DERBY_DATA_BASE.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("UPDATE APP.PERSON t SET t.SURNAME = ?, t.NAME = ?, " +
                             "t.PATRONYMIC = ?, t.POST = ?, t.DATA_OF_BIRTH = ?, t.PHONE_NUMBER  = ? WHERE t.ID = ?")) {
            preparedStatement.setString(1, object.getSurname());
            preparedStatement.setString(2, object.getName());
            preparedStatement.setString(3, object.getPatronymic());
            preparedStatement.setString(4, object.getPost());
            preparedStatement.setDate(5, new Date(object.getDateOfBirth().getTime()));
            preparedStatement.setInt(6, object.getPhoneNumber());
            preparedStatement.setInt(7, (int) object.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SaveObjectException("Ошибка при обновления объекта Person c id " + object.getId());
        }
    }

    /**
     * Получить список всех сохраненных объектов класса <code>Person</code>
     *
     * @return список сохраненных объектов класса <code>Person</code>
     */
    @Override
    public List<Person> getAll() {
        List<Person> personList = new ArrayList<>();
        try (Connection connection = SESSION_DERBY_DATA_BASE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PERSON");
             ResultSet rs = preparedStatement.executeQuery()) {
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
            LOGGER.error("Ошибка доступа к базе данных или этот метод вызывается при закрытом соединении", e);
        }
        return personList;
    }

    /**
     * Сохранить список объектов класса <code>Person</code>
     * Сохранение происходит в рамках одной транзакции, в случае неудачи отменяются все изменения
     *
     * @param personList список объектов класса <code>Person</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void saveAll(List<Person> personList) throws SaveObjectException {
        try (Connection connection = SESSION_DERBY_DATA_BASE.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO APP.PERSON (ID, SURNAME, NAME, PATRONYMIC, POST, DATA_OF_BIRTH, PHONE_NUMBER)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)")) {
                connection.setAutoCommit(false);
                for (Person person : personList) {
                    preparedStatement.setInt(1, (int) person.getId());
                    preparedStatement.setString(2, person.getSurname());
                    preparedStatement.setString(3, person.getName());
                    preparedStatement.setString(4, person.getPatronymic());
                    preparedStatement.setString(5, person.getPost());
                    preparedStatement.setDate(6, new Date(person.getDateOfBirth().getTime()));
                    preparedStatement.setInt(7, person.getPhoneNumber());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SaveObjectException("Ошибка сохранения объекта Person " + e);
            }
        } catch (SQLException e) {
            throw new SaveObjectException("Ошибка сохранения объекта Person" + e);
        }
    }

    /**
     * Сохранить объект класса <code>Person</code>
     *
     * @param person объект класса <code>Person</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void save(Person person) throws SaveObjectException {
        saveAll(Arrays.asList(person));
    }

    /**
     * Найти объект класса <code>Person</code> по id
     *
     * @param id id объекта класса <code>Person</code>
     * @return найденный объект класса <code>Person</code>
     */
    @Override
    public Optional<Person> findById(long id) {
        Person person = new Person();
        try (Connection connection = SESSION_DERBY_DATA_BASE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID=?")) {
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
            LOGGER.error("Ошибка доступа к базе данных или этот метод вызывается при закрытом соединении", e);
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
