package com.example.document_flow.DAO.implement.dao.staff;

import com.example.document_flow.DAO.abstraction.OrganizationDAO;
import com.example.document_flow.config.DataBase.abstraction.DataSourceManager;
import com.example.document_flow.config.DataBase.implement.DataSourceManagerImpl;
import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.mappers.absraction.OrganizationMapper;
import com.example.document_flow.mappers.implement.document.OrganizationMapperImpl;

import java.sql.Connection;
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
 * Содержит методы связанные с сущностью {@link Organization}
 *
 * @author Баратов Руслан
 */
public class OrganizationDAOImpl implements OrganizationDAO {

    private static final String SQL_FIND_ORGANIZATION_BY_ID = "SELECT * FROM ORGANIZATION " +
            "LEFT JOIN PERSON P on P.ID = ORGANIZATION.MANAGER_ID where ORGANIZATION.ID =?";

    private static final String SQL_DELETE_ORGANIZATION_BY_ID = "DELETE FROM APP.ORGANIZATION WHERE ID = ?";

    private static final String SQL_UPDATE_ORGANIZATION = "UPDATE APP.ORGANIZATION t SET t.FULL_NAME = ?," +
            " t.SHORT_NAME = ?, t.CONTACT_PHONE_NUMBER = ? WHERE t.ID = ?";

    private static final String SQL_GET_ALL_ORGANIZATION = "SELECT * FROM ORGANIZATION " +
            "LEFT JOIN PERSON P on P.ID = ORGANIZATION.MANAGER_ID";

    private static final String SQL_SAVE_ALL = "INSERT INTO APP.ORGANIZATION (FULL_NAME, SHORT_NAME, MANAGER_ID, CONTACT_PHONE_NUMBER, ID)\n" +
            "VALUES (?, ?, ?, ?, ?)";

    private static OrganizationDAOImpl derbyDataBase;

    private final DataSourceManager sessionManager = DataSourceManagerImpl.getInstance();

    private final OrganizationMapper organizationMapper = OrganizationMapperImpl.getInstance();

    private OrganizationDAOImpl() {
    }

    /**
     * @return синголтон обьект
     */
    public static OrganizationDAOImpl getInstance() {
        if (derbyDataBase == null) {
            derbyDataBase = new OrganizationDAOImpl();
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
        try (Connection connection = sessionManager.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ORGANIZATION_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteObjectException(MessageFormat.format("Ошибка удаление Organization c id {0}", id), e);
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
        try (Connection connection = sessionManager.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORGANIZATION)) {
            preparedStatement.setString(1, object.getFullName());
            preparedStatement.setString(2, object.getShortName());
            preparedStatement.setLong(3, object.getManager().getId());
            preparedStatement.setString(4, object.getContactPhoneNumber());
            preparedStatement.setLong(5, object.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SaveObjectException(MessageFormat.format("Ошибка при обновления объекта Organization c id {0}", object.getId()), e);
        }
    }

    /**
     * Получить список всех сохраненных объектов класса {@link Organization}
     *
     * @return список сохраненных объектов класса {@link Organization}
     */
    @Override
    public List<Organization> getAll() throws GetDataObjectException {
        List<Organization> organizationList = new ArrayList<>();
        try (Connection connection = sessionManager.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_ORGANIZATION);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                organizationList.add(organizationMapper.convertFrom(rs));
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
        }
        return organizationList;
    }

    /**
     * Сохранить список объектов класса {@link Organization}
     *
     * @param organizationList список объектов класса {@link Organization}для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void saveAll(List<Organization> organizationList) throws SaveObjectException {
        try (Connection connection = sessionManager.getDataSource().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ALL)) {
                for (Organization organization : organizationList) {
                    preparedStatement.setString(1, organization.getFullName());
                    preparedStatement.setString(2, organization.getShortName());
                    if (organization.getManager() == null) {
                        preparedStatement.setNull(3, Types.BIGINT);
                    } else {
                        preparedStatement.setLong(3, organization.getManager().getId());
                    }
                    preparedStatement.setString(4, organization.getContactPhoneNumber());
                    preparedStatement.setLong(5, organization.getId());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                throw new SaveObjectException("Ошибка сохранения объекта Organization ", e);
            }
        } catch (SQLException e) {
            throw new SaveObjectException("Ошибка сохранения объекта Organization ", e);
        }
    }

    /**
     * Сохранить объект класса {@link Organization}
     *
     * @param organization объект класса <code>Organization</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void save(Organization organization) throws SaveObjectException {
        saveAll(List.of(organization));
    }

    /**
     * Найти объект класса {@link Organization} по id
     *
     * @param id id объекта класса {@link Organization}
     * @return найденный объект класса {@link Organization}
     */
    @Override
    public Optional<Organization> findById(long id) throws GetDataObjectException {
        Organization organization = new Organization();
        try (Connection connection = sessionManager.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ORGANIZATION_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    organization = organizationMapper.convertFrom(rs);
                }
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
        }
        return Optional.ofNullable(organization);
    }
}
