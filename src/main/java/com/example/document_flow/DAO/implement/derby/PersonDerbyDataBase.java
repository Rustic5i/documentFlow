package com.example.document_flow.DAO.implement.derby;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.DAO.DTO.PreparedStatementDTO;
import com.example.document_flow.DAO.DTO.ResultSetDTO;
import com.example.document_flow.config.DataBase.abstraction.SessionDataBase;
import com.example.document_flow.config.DataBase.implement.SessionDerbyDataBase;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.SaveObjectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
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

    private static PersonDerbyDataBase derbyDataBase;

    private final SessionDataBase SESSION_DERBY_DATA_BASE = SessionDerbyDataBase.getInstance();

    private final Logger LOGGER = LoggerFactory.getLogger(PersonDerbyDataBase.class.getName());

    private final String SQL_DELETE_PERSON_BY_ID = "DELETE FROM APP.PERSON WHERE ID = ?";

    private final String SQL_UPDATE_PERSON = "UPDATE APP.PERSON t SET t.SURNAME = ?, t.NAME = ?, " +
            "t.PATRONYMIC = ?, t.POST = ?, t.DATA_OF_BIRTH = ?, t.PHONE_NUMBER  = ? WHERE t.ID = ?";

    private final String SQL_GET_ALL_PERSON = "SELECT * FROM PERSON";

    private final String SQL_SAVE_PERSON = "INSERT INTO APP.PERSON (SURNAME, NAME, PATRONYMIC, POST, DATA_OF_BIRTH, PHONE_NUMBER,ID)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private final String SQL_FIND_PERSON_BY_ID = "SELECT * FROM PERSON WHERE ID=?";

    private PersonDerbyDataBase() {
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

    /**
     * Удалить по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        try (PreparedStatement preparedStatement = SESSION_DERBY_DATA_BASE.getConnection().prepareStatement(SQL_DELETE_PERSON_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteObjectException("Ошибка удаление Person c id " + id + e);
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
        try (PreparedStatement preparedStatement = SESSION_DERBY_DATA_BASE.getConnection().prepareStatement(SQL_UPDATE_PERSON)) {
            PreparedStatementDTO.transfer(object, preparedStatement);
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
        try (ResultSet rs = SESSION_DERBY_DATA_BASE.getConnection().prepareStatement(SQL_GET_ALL_PERSON).executeQuery()) {
            while (rs.next()) {
                personList.add(ResultSetDTO.transferPerson(rs));
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
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_PERSON)) {
                connection.setAutoCommit(false);
                for (Person person : personList) {
                    PreparedStatementDTO.transfer(person, preparedStatement);
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
        try (PreparedStatement preparedStatement = SESSION_DERBY_DATA_BASE.getConnection().prepareStatement(SQL_FIND_PERSON_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery();) {
                while (rs.next()) {
                    person = ResultSetDTO.transferPerson(rs);
                }
            } catch (SQLException e) {
                LOGGER.error("Ошибка получения ResultSet ", e);
            }
        } catch (SQLException e) {
            LOGGER.error("Ошибка доступа к базе данных или этот метод вызывается при закрытом соединении", e);
        }
        return Optional.of(person);
    }
}
