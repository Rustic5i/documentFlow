package com.example.document_flow.DAO.implement.dao.staff;

import com.example.document_flow.DAO.abstraction.PersonDAO;
import com.example.document_flow.config.DataBase.abstraction.DataSourceManager;
import com.example.document_flow.config.DataBase.implement.DataSourceManagerImpl;
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
import java.sql.Types;
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
public class PersonDAOImpl implements PersonDAO {

    private static final String SQL_DELETE_PERSON_BY_ID = "DELETE FROM APP.PERSON WHERE ID = ?";

    private static final String SQL_UPDATE_PERSON = "UPDATE APP.PERSON t SET t.SURNAME = ?, t.NAME = ?, " +
            "t.PATRONYMIC = ?, t.POST = ?, t.DATA_OF_BIRTH = ?, t.PHONE_NUMBER  = ?, t.ID_DEPARTMENT = ? WHERE t.ID = ?";

    private static final String SQL_GET_ALL_PERSON = "SELECT * FROM PERSON P " +
            "JOIN DEPARTMENT D ON P.ID_DEPARTMENT = D.ID";

    private static final String SQL_SAVE_PERSON = "INSERT INTO APP.PERSON " +
            "(SURNAME, NAME, PATRONYMIC, POST, DATA_OF_BIRTH, PHONE_NUMBER,ID,ID_DEPARTMENT)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?,?)";

    private static final String SQL_FIND_PERSON_BY_ID = "SELECT * FROM PERSON P " +
            "JOIN DEPARTMENT D ON P.ID_DEPARTMENT = D.ID WHERE P.ID=?";

    private static final String SQL_FIND_BY_ID_DEPARTMENT = "SELECT * FROM PERSON PERSON " +
            "JOIN DEPARTMENT D ON PERSON.ID_DEPARTMENT = D.ID WHERE PERSON.ID_DEPARTMENT = ?";

    private static PersonDAOImpl derbyDataBase;

    private final DataSourceManager sourceManager = DataSourceManagerImpl.getInstance();

    private final PersonMapper personMapper = PersonMapperImpl.getInstance();

    private PersonDAOImpl() {
    }

    /**
     * @return синголтон объект
     */
    public static PersonDAOImpl getInstance() {
        if (derbyDataBase == null) {
            derbyDataBase = new PersonDAOImpl();
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
        try (PreparedStatement preparedStatement = sourceManager.getDataSource().getConnection().prepareStatement(SQL_DELETE_PERSON_BY_ID)) {
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
        try (PreparedStatement preparedStatement = sourceManager.getDataSource().getConnection().prepareStatement(SQL_UPDATE_PERSON)) {
            preparedStatement.setString(1, object.getSurname());
            preparedStatement.setString(2, object.getName());
            preparedStatement.setString(3, object.getPatronymic());
            preparedStatement.setString(4, object.getPost());
            preparedStatement.setDate(5, new Date(object.getDateOfBirth().getTime()));
            preparedStatement.setInt(6, object.getPhoneNumber());
            preparedStatement.setLong(7, object.getDepartment().getId());
            preparedStatement.setLong(8, object.getId());
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
        try (ResultSet rs = sourceManager
                .getDataSource().getConnection().prepareStatement(SQL_GET_ALL_PERSON).executeQuery()) {
            while (rs.next()) {
                personList.add(personMapper.convertFrom(rs));
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
        try (PreparedStatement preparedStatement = sourceManager.getDataSource().getConnection().prepareStatement(SQL_SAVE_PERSON)) {
            for (Person person : personList) {
                preparedStatement.setString(1, person.getSurname());
                preparedStatement.setString(2, person.getName());
                preparedStatement.setString(3, person.getPatronymic());
                preparedStatement.setString(4, person.getPost());
                preparedStatement.setDate(5, new Date(person.getDateOfBirth().getTime()));
                preparedStatement.setInt(6, person.getPhoneNumber());
                preparedStatement.setLong(7, person.getId());
                if (person.getDepartment() == null) {
                    preparedStatement.setNull(8, Types.BIGINT);
                } else {
                    preparedStatement.setLong(8, person.getDepartment().getId());
                }
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
        try (PreparedStatement preparedStatement = sourceManager
                .getDataSource().getConnection().prepareStatement(SQL_FIND_PERSON_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    person = personMapper.convertFrom(rs);
                }
            } catch (SQLException e) {
                throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
        }
        return Optional.ofNullable(person);
    }

    @Override
    public List<Person> findByIdDepartment(long id) throws GetDataObjectException {
        List<Person> personList = new ArrayList<>();
        try (PreparedStatement preparedStatement = sourceManager
                .getDataSource().getConnection().prepareStatement(SQL_FIND_BY_ID_DEPARTMENT)) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    personList.add(personMapper.convertFrom(rs));
                }
            } catch (SQLException e) {
                throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
        }
        return personList;
    }
}
