package com.example.document_flow.DAO.implement.derby;

import com.example.document_flow.DAO.DTO.PreparedStatementDTO;
import com.example.document_flow.DAO.DTO.ResultSetDTO;
import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.config.DataBase.abstraction.SessionDataBase;
import com.example.document_flow.config.DataBase.implement.SessionManager;
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

    private static final String SQL_FIND_ORGANIZATION_BY_ID = "SELECT * FROM ORGANIZATION " +
            "JOIN PERSON P on P.ID = ORGANIZATION.MANAGER_ID where ORGANIZATION.ID =?";

    private static final String SQL_DELETE_ORGANIZATION_BY_ID = "DELETE FROM APP.ORGANIZATION WHERE ID = ?";

    private static final String SQL_UPDATE_ORGANIZATION = "UPDATE APP.ORGANIZATION t SET t.FULL_NAME = ?," +
            " t.SHORT_NAME = ?, t.CONTACT_PHONE_NUMBER = ? WHERE t.ID = ?";

    private static final String SQL_GET_ALL_ORGANIZATION = "SELECT * FROM ORGANIZATION";

    private static final String SQL_SAVE_ALL = "INSERT INTO APP.ORGANIZATION (FULL_NAME, SHORT_NAME, MANAGER_ID, CONTACT_PHONE_NUMBER, ID)\n" +
            "VALUES (?, ?, ?, ?, ?)";

    private static OrganizationDerbyDataBase derbyDataBase;

    private final SessionDataBase SESSION_MANAGER = SessionManager.getInstance();

    private final Logger LOGGER = LoggerFactory.getLogger(OrganizationDerbyDataBase.class.getName());

    private OrganizationDerbyDataBase() {
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

    /**
     * Удалить по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        try (PreparedStatement preparedStatement = SESSION_MANAGER.getConnection().prepareStatement(SQL_DELETE_ORGANIZATION_BY_ID)) {
            preparedStatement.setLong(1, id);
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
        try (PreparedStatement preparedStatement = SESSION_MANAGER.getConnection().prepareStatement(SQL_UPDATE_ORGANIZATION)) {
            PreparedStatementDTO.transfer(object, preparedStatement);
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
        try (ResultSet rs = SESSION_MANAGER.getConnection().prepareStatement(SQL_GET_ALL_ORGANIZATION).executeQuery()) {
            while (rs.next()) {
                organizationList.add(ResultSetDTO.transferOrganization(rs));
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
        try (Connection connection = SESSION_MANAGER.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ALL)) {
                connection.setAutoCommit(false);
                for (Organization organization : organizationList) {
                    PreparedStatementDTO.transfer(organization, preparedStatement);
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SaveObjectException("Ошибка сохранения объекта Organization " + e);
            }
        } catch (SQLException e) {
            throw new SaveObjectException("Ошибка сохранения объекта Organization " + e);
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
        try (PreparedStatement preparedStatement = SESSION_MANAGER.getConnection().prepareStatement(SQL_FIND_ORGANIZATION_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                organization = ResultSetDTO.transferOrganization(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("Ошибка доступа к базе данных или этот метод вызывается при закрытом соединении", e);
        }
        return Optional.of(organization);
    }
}
