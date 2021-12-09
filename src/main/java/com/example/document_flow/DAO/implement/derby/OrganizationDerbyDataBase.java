package com.example.document_flow.DAO.implement.derby;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.config.DataBase.abstraction.SessionDataBase;
import com.example.document_flow.config.DataBase.implement.SessionDerbyDataBase;
import com.example.document_flow.entity.staff.Organization;
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
 * Содержит методы связанные с сущностью <code>Organization</code>
 *
 * @author Баратов Руслан
 */
public class OrganizationDerbyDataBase implements DAOCrud<Organization> {

    private final PersonDerbyDataBase PERSON_DERBY = PersonDerbyDataBase.getInstance();

    private final SessionDataBase SESSION_DERBY_DATA_BASE = SessionDerbyDataBase.getInstance();

    private final Logger LOGGER = LoggerFactory.getLogger(OrganizationDerbyDataBase.class.getName());

    private static OrganizationDerbyDataBase derbyDataBase;

    private OrganizationDerbyDataBase() {
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
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM APP.ORGANIZATION WHERE ID = ?")) {
            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteObjectException("Ошибка удаление Organization c id " + id);
        }
    }

    /**
     * Обновить данные объекта
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(Organization object) throws SaveObjectException {
        try (Connection connection = SESSION_DERBY_DATA_BASE.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("UPDATE APP.ORGANIZATION t SET t.FULL_NAME = ?, t.SHORT_NAME = ?, t.CONTACT_PHONE_NUMBER = ? WHERE t.ID = ?")) {
            preparedStatement.setString(1, object.getFullName());
            preparedStatement.setString(2, object.getShortName());
            preparedStatement.setString(3, object.getContactPhoneNumber());
            preparedStatement.setInt(4, (int) object.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SaveObjectException("Ошибка при обновления объекта Organization c id " + object.getId());
        }
    }

    /**
     * Получить список всех сохраненных объектов класса <code>Organization</code>
     *
     * @return список сохраненных объектов класса <code>Organization</code>
     */
    @Override
    public List<Organization> getAll() {
        List<Organization> organizationList = new ArrayList<>();
        try (Connection connection = SESSION_DERBY_DATA_BASE.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT * FROM ORGANIZATION");
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                organizationList.add(new Organization().newBuilder()
                        .setId(rs.getInt(1))
                        .setFullName(rs.getString(2))
                        .setShortName(rs.getString(3))
                        .setManager(PERSON_DERBY.findById(rs.getInt(4)).get())
                        .setContactPhoneNumber(rs.getString(5))
                        .build());
            }
        } catch (SQLException e) {
            LOGGER.error("Ошибка доступа к базе данных или этот метод вызывается при закрытом соединении", e);
        }
        return organizationList;
    }

    /**
     * Сохранить список объектов класса <code>Organization</code>
     *
     * @param organizationList список объектов класса <code>Organization</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void saveAll(List<Organization> organizationList) throws SaveObjectException {
        try (Connection connection = SESSION_DERBY_DATA_BASE.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO APP.ORGANIZATION (ID, FULL_NAME, SHORT_NAME, MANAGER_ID, CONTACT_PHONE_NUMBER)\n" +
                            "VALUES (?, ?, ?, ?, ?)")) {
                connection.setAutoCommit(false);
                for (Organization organization : organizationList) {
                    preparedStatement.setInt(1, (int) organization.getId());
                    preparedStatement.setString(2, organization.getFullName());
                    preparedStatement.setString(3, organization.getShortName());
                    preparedStatement.setInt(4, (int) organization.getManager().getId());
                    preparedStatement.setString(5, organization.getContactPhoneNumber());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SaveObjectException("Ошибка сохранения объекта Organization " + e);
            }
        } catch (SQLException e) {
            throw new SaveObjectException("Ошибка сохранения объекта Organization" + e);
        }
    }

    /**
     * Сохранить объект класса <code>Organization</code>
     *
     * @param organization объект класса <code>Organization</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void save(Organization organization) throws SaveObjectException {
        saveAll(Arrays.asList(organization));
    }

    /**
     * Найти объект класса <code>Organization</code> по id
     *
     * @param id id объекта класса <code>Organization</code>
     * @return найденный объект класса <code>Organization</code>
     */
    @Override
    public Optional<Organization> findById(long id) {
        Organization organization = new Organization();
        try (Connection connection = SESSION_DERBY_DATA_BASE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ORGANIZATION WHERE ID=?");
             ResultSet rs = preparedStatement.executeQuery()) {
            preparedStatement.setLong(1, id);
            while (rs.next()) {
                organization.newBuilder()
                        .setId(rs.getInt(1))
                        .setFullName(rs.getString(2))
                        .setShortName(rs.getString(3))
                        .setManager(PERSON_DERBY.findById(rs.getInt(4)).get())
                        .setContactPhoneNumber(rs.getString(5))
                        .build();
            }
        } catch (SQLException e) {
            LOGGER.error("Ошибка доступа к базе данных или этот метод вызывается при закрытом соединении", e);
        }
        return Optional.of(organization);
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
