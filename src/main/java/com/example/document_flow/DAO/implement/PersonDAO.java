package com.example.document_flow.DAO.implement;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.config.DataBase.abstraction.SessionManager;
import com.example.document_flow.config.DataBase.implement.SessionManagerImp;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.mappers.absraction.PersonMapper;
import com.example.document_flow.mappers.implement.PersonMapperImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO слой отвечает за предоставления доступа к базе данных.
 * Содержит методы связанные с сущностью <code>Person</code>
 *
 * @author Баратов Руслан
 */
public class PersonDAO implements DAOCrud<Person> {

    private static final String SQL_DELETE_PERSON_BY_ID = "DELETE FROM APP.PERSON WHERE ID = ?";

    private static final String SQL_UPDATE_PERSON = "UPDATE APP.PERSON t SET t.SURNAME = ?, t.NAME = ?, " +
            "t.PATRONYMIC = ?, t.POST = ?, t.DATA_OF_BIRTH = ?, t.PHONE_NUMBER  = ? WHERE t.ID = ?";

    private static final String SQL_GET_ALL_PERSON = "SELECT * FROM PERSON";

    private static final String SQL_SAVE_PERSON = "INSERT INTO APP.PERSON (SURNAME, NAME, PATRONYMIC, POST, DATA_OF_BIRTH, PHONE_NUMBER,ID)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_FIND_PERSON_BY_ID = "SELECT * FROM PERSON WHERE ID=?";

    private static PersonDAO derbyDataBase;

    private final SessionManager SESSION_MANAGER = SessionManagerImp.getInstance();

    private final PersonMapper PERSON_MAPPER = PersonMapperImpl.getInstance();

    private PersonDAO() {
    }

    /**
     * @return синголтон обьект
     */
    public static PersonDAO getInstance() {
        if (derbyDataBase == null) {
            derbyDataBase = new PersonDAO();
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
        try (PreparedStatement preparedStatement = SESSION_MANAGER.getDataSource().getConnection().prepareStatement(SQL_DELETE_PERSON_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteObjectException(MessageFormat.format("Ошибка удаление Person c id {0}", id), e);
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
        try (PreparedStatement preparedStatement = SESSION_MANAGER.getDataSource().getConnection().prepareStatement(SQL_UPDATE_PERSON)) {
            preparedStatement.setString(1, object.getSurname());
            preparedStatement.setString(2, object.getName());
            preparedStatement.setString(3, object.getPatronymic());
            preparedStatement.setString(4, object.getPost());
            preparedStatement.setDate(5, new Date(object.getDateOfBirth().getTime()));
            preparedStatement.setInt(6, object.getPhoneNumber());
            preparedStatement.setLong(7, object.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SaveObjectException(MessageFormat.format("Ошибка при обновления объекта Person c id {0}", object.getId()), e);
        }
    }

    /**
     * Получить список всех сохраненных объектов класса <code>Person</code>
     *
     * @return список сохраненных объектов класса <code>Person</code>
     */
    @Override
    public List<Person> getAll() throws GetDataObjectException {
        List<Person> personList = new ArrayList<>();
        try (ResultSet rs = SESSION_MANAGER.getDataSource().getConnection().prepareStatement(SQL_GET_ALL_PERSON).executeQuery()) {
            while (rs.next()) {
                personList.add(PERSON_MAPPER.convertFrom(rs));
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
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
        try (PreparedStatement preparedStatement = SESSION_MANAGER.getDataSource().getConnection().prepareStatement(SQL_SAVE_PERSON)) {
            for (Person person : personList) {
                preparedStatement.setString(1, person.getSurname());
                preparedStatement.setString(2, person.getName());
                preparedStatement.setString(3, person.getPatronymic());
                preparedStatement.setString(4, person.getPost());
                preparedStatement.setDate(5, new Date(person.getDateOfBirth().getTime()));
                preparedStatement.setInt(6, person.getPhoneNumber());
                preparedStatement.setLong(7, person.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new SaveObjectException("Ошибка сохранения объекта Person ", e);
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
        saveAll(List.of(person));
    }

    /**
     * Найти объект класса <code>Person</code> по id
     *
     * @param id id объекта класса <code>Person</code>
     * @return найденный объект класса <code>Person</code>
     */
    @Override
    public Optional<Person> findById(long id) throws GetDataObjectException {
        Person person = new Person();
        try (PreparedStatement preparedStatement = SESSION_MANAGER.getDataSource().getConnection().prepareStatement(SQL_FIND_PERSON_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    person = PERSON_MAPPER.convertFrom(rs);
                }
            } catch (SQLException e) {
                throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
        }
        return Optional.of(person);
    }
}
