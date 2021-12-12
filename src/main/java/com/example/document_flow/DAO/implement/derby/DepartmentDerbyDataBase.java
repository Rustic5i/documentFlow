package com.example.document_flow.DAO.implement.derby;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.config.DataBase.abstraction.SessionDataBase;
import com.example.document_flow.config.DataBase.implement.SessionDerbyDataBase;
import com.example.document_flow.entity.staff.Department;
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
 * Содержит методы связанные с сущностью <code>Department</code>
 *
 * @author Баратов Руслан
 */
public class DepartmentDerbyDataBase implements DAOCrud<Department> {

    private PersonDerbyDataBase personDerbyDataBase = PersonDerbyDataBase.getInstance();

    private final SessionDataBase SESSION_DERBY_DATA_BASE = SessionDerbyDataBase.getInstance();

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentDerbyDataBase.class.getName());

    private static DepartmentDerbyDataBase derbyDataBase;

    private DepartmentDerbyDataBase() {
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
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM APP.DEPARTMENT WHERE ID = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteObjectException("Ошибка удаление Department c id " + id);
        }
    }

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(Department object) throws SaveObjectException {
        try (Connection connection = SESSION_DERBY_DATA_BASE.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("UPDATE APP.DEPARTMENT t SET t.FULL_NAME = ?, t.SHORT_NAME = ?, t.CONTACT_PHONE_NUMBER = ? WHERE t.ID = ?")) {
            preparedStatement.setString(1, object.getFullName());
            preparedStatement.setString(2, object.getShortName());
            preparedStatement.setString(3, object.getContactPhoneNumber());
            preparedStatement.setLong(4, object.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SaveObjectException("Ошибка при обновления объекта Department c id " + object.getId());
        }
    }

    /**
     * Получить список всех сохраненных объектов класса <code>Department</code>
     *
     * @return список сохраненных объектов класса <code>Department</code>
     */
    @Override
    public List<Department> getAll() {
        List<Department> departmentList = new ArrayList<>();
        try (Connection connection = SESSION_DERBY_DATA_BASE.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT * FROM DEPARTMENT");
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                departmentList.add(new Department().newBuilder()
                        .setId(rs.getLong(1))
                        .setFullName(rs.getString(2))
                        .setShortName(rs.getString(3))
                        .setManager(personDerbyDataBase.findById(rs.getLong(4)).get())
                        .setContactPhoneNumber(rs.getString(5))
                        .build());
            }
        } catch (SQLException e) {
            LOGGER.error("Ошибка доступа к базе данных или этот метод вызывается при закрытом соединении", e);
        }
        return departmentList;
    }

    /**
     * Сохранить список объектов класса <code>Department</code>
     *
     * @param departmentList список объектов класса <code>Department</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void saveAll(List<Department> departmentList) throws SaveObjectException {
        try (Connection connection = SESSION_DERBY_DATA_BASE.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO APP.DEPARTMENT (ID, FULL_NAME, SHORT_NAME, MANAGER_ID, CONTACT_PHONE_NUMBER)\n" +
                            "VALUES (?, ?, ?, ?, ?)")) {
                connection.setAutoCommit(false);
                for (Department department : departmentList) {
                    preparedStatement.setLong(1, department.getId());
                    preparedStatement.setString(2, department.getFullName());
                    preparedStatement.setString(3, department.getShortName());
                    preparedStatement.setLong(4, department.getManager().getId());
                    preparedStatement.setString(5, department.getContactPhoneNumber());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SaveObjectException("Ошибка сохранения объекта Department " + e);
            }
        } catch (SQLException e) {
            throw new SaveObjectException("Ошибка сохранения объекта Department" + e);
        }
    }

    /**
     * Сохранить объект класса  <code>Department</code>
     *
     * @param department объект класса <code>Department</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void save(Department department) throws SaveObjectException {
        saveAll(Arrays.asList(department));
    }

    /**
     * Найти объект класса <code>Department</code> по id
     *
     * @param id id объекта класса <code>Department</code>
     * @return найденный объект класса <code>Department</code>
     */
    @Override
    public Optional<Department> findById(long id) {
        Department department = new Department();
        try (Connection connection = SESSION_DERBY_DATA_BASE.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT * FROM DEPARTMENT WHERE ID=?")) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                department.newBuilder()
                        .setId(rs.getLong(1))
                        .setFullName(rs.getString(2))
                        .setShortName(rs.getString(3))
                        .setManager(personDerbyDataBase.findById(rs.getLong(4)).get())
                        .setContactPhoneNumber(rs.getString(5))
                        .build();
            }
        } catch (SQLException e) {
            LOGGER.error("Ошибка доступа к базе данных или этот метод вызывается при закрытом соединении", e);
        }
        return Optional.of(department);
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
