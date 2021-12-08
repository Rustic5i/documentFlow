package com.example.document_flow.DAO.implement.derby;

import com.example.document_flow.DAO.abstraction.OrganizationDAO;
import com.example.document_flow.config.DataBase.abstraction.SessionDataBase;
import com.example.document_flow.config.DataBase.implement.SessionDerbyDataBase;
import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.exception.SaveObjectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO слой отвечает за предоставления доступа к базе данных.
 * Содержит методы связанные с сущностью <code>Organization</code>
 *
 * @author Баратов Руслан
 */
public class OrganizationDerbyDataBase implements OrganizationDAO {

    private Connection connection;

    private PreparedStatement preparedStatement;

    private PersonDerbyDataBase personDerbyDataBase = PersonDerbyDataBase.getInstance();

    private final SessionDataBase SESSION_DERBY_DATA_BASE = SessionDerbyDataBase.getInstance();

    private final Logger LOGGER = LoggerFactory.getLogger(OrganizationDerbyDataBase.class.getName());

    private static OrganizationDerbyDataBase derbyDataBase;

    private OrganizationDerbyDataBase() {
        connectToDB();
    }

    /***
     * Получение соединения (сеанса) к бд Derby
     */
    private void connectToDB() {
        connection = SESSION_DERBY_DATA_BASE.getConnection();
    }

    /**
     * Сохранить объект класса <code>Organization</code>
     *
     * @param organization объект класса <code>Organization</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void saveOrganization(Organization organization) throws SaveObjectException {
        try {
            preparedStatement = connection
                    .prepareStatement("INSERT INTO APP.ORGANIZATION (ID, FULL_NAME, SHORT_NAME, MANAGER_ID, CONTACT_PHONE_NUMBER)\n" +
                            "VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, (int) organization.getId());
            preparedStatement.setString(2, organization.getFullName());
            preparedStatement.setString(3, organization.getShortName());
            preparedStatement.setInt(4, (int) organization.getManager().getId());
            preparedStatement.setString(5, organization.getContactPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SaveObjectException("Organization с id " + organization.getId() + " уже существует " + e);
        } catch (SQLException e) {
            LOGGER.error("Ошибка доступа к базе данных или этот метод вызывается при закрытом соединении", e);
        }
    }

    /**
     * Получить список всех сохраненных объектов класса <code>Organization</code>
     *
     * @return список сохраненных объектов класса <code>Organization</code>
     */
    @Override
    public List<Organization> getAllOrganization() {
        List<Organization> organizationList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM ORGANIZATION");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                organizationList.add(new Organization().newBuilder()
                        .setId(rs.getInt(1))
                        .setFullName(rs.getString(2))
                        .setShortName(rs.getString(3))
                        .setManager(personDerbyDataBase.findPersonById(rs.getInt(4)).get())
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
    public void saveAllOrganization(List<Organization> organizationList) throws SaveObjectException {
        try {
            connection.setAutoCommit(false);
            for (Organization organization : organizationList) {
                saveOrganization(organization);
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("Ошибка при попытки зафиксировать изменения ", e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.error("Ошибка при попытки отменить транзакцию ", ex);
            }
        }
    }

    /**
     * Найти объект класса <code>Organization</code> по id
     *
     * @param id id объекта класса <code>Organization</code>
     * @return найденный объект класса <code>Organization</code>
     */
    @Override
    public Optional<Organization> findOrganizationById(long id) {
        Organization organization = new Organization();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM ORGANIZATION WHERE ID=?");
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                organization.newBuilder()
                        .setId(rs.getInt(1))
                        .setFullName(rs.getString(2))
                        .setShortName(rs.getString(3))
                        .setManager(personDerbyDataBase.findPersonById(rs.getInt(4)).get())
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
