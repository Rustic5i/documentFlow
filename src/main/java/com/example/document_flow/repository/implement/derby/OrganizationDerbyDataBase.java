package com.example.document_flow.repository.implement.derby;

import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.repository.absraction.dao.OrganizationDAO;

import java.sql.Connection;
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
 * Содержит методы связанные с сущностью <code>Organization</code>
 *
 * @author Баратов Руслан
 */
public class OrganizationDerbyDataBase implements OrganizationDAO {

    private Connection connection;

    private Statement statement;

    private PreparedStatement preparedStatement;

    private PersonDerbyDataBase personDerbyDataBase = PersonDerbyDataBase.getInstance();

    private static OrganizationDerbyDataBase derbyDataBase;

    private OrganizationDerbyDataBase() {
        connectToDB();
        //  createOrganizationTable();
    }

    private void connectToDB() {
        try {
            connection = SessionDerbyDataBase.connectToDB();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createOrganizationTable() {
        try {
            statement.executeUpdate("create table organization\n" +
                    "(\n" +
                    "\tid int not null\n" +
                    "\t\tconstraint ORGANIZATION_PK\n" +
                    "\t\t\tprimary key,\n" +
                    "\tfull_name varchar(25),\n" +
                    "\tshort_name varchar(25),\n" +
                    "\tmanager_id int\n" +
                    "\t\tconstraint manager\n" +
                    "\t\t\treferences PERSON,\n" +
                    "\tcontact_phone_number varchar(25)\n" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
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
            e.printStackTrace();
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
