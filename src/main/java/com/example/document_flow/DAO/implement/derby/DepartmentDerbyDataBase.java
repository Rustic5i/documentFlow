package com.example.document_flow.DAO.implement.derby;

import com.example.document_flow.DAO.abstraction.DepartmentDAO;
import com.example.document_flow.config.DataBase.abstraction.SessionDataBase;
import com.example.document_flow.config.DataBase.implement.SessionDerbyDataBase;
import com.example.document_flow.entity.staff.Department;
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
 * Содержит методы связанные с сущностью <code>Department</code>
 *
 * @author Баратов Руслан
 */
public class DepartmentDerbyDataBase implements DepartmentDAO {

    private Connection connection;

    private PreparedStatement preparedStatement;

    private PersonDerbyDataBase personDerbyDataBase = PersonDerbyDataBase.getInstance();

    private final SessionDataBase SESSION_DERBY_DATA_BASE = SessionDerbyDataBase.getInstance();

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentDerbyDataBase.class.getName());

    private static DepartmentDerbyDataBase derbyDataBase;

    private DepartmentDerbyDataBase() {
        connectToDB();
    }

    /***
     * Получение соединения (сеанса) к бд Derby
     */
    private void connectToDB() {
        connection = SESSION_DERBY_DATA_BASE.getConnection();
    }

    /**
     * Сохранить объект класса  <code>Department</code>
     *
     * @param department объект класса <code>Department</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void saveDepartment(Department department) throws SaveObjectException {
        try {
            preparedStatement = connection
                    .prepareStatement("INSERT INTO APP.DEPARTMENT (ID, FULL_NAME, SHORT_NAME, MANAGER_ID, CONTACT_PHONE_NUMBER)\n" +
                            "VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, (int) department.getId());
            preparedStatement.setString(2, department.getFullName());
            preparedStatement.setString(3, department.getShortName());
            preparedStatement.setInt(4, (int) department.getManager().getId());
            preparedStatement.setString(5, department.getContactPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SaveObjectException("Department с id " + department.getId() + " уже существует " + e);
        } catch (SQLException e) {
            LOGGER.error("Ошибка доступа к базе данных или этот метод вызывается при закрытом соединении", e);
        }
    }

    /**
     * Получить список всех сохраненных объектов класса <code>Department</code>
     *
     * @return список сохраненных объектов класса <code>Department</code>
     */
    @Override
    public List<Department> getAllDepartment() {
        List<Department> departmentList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM DEPARTMENT");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                departmentList.add(new Department().newBuilder()
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
        return departmentList;
    }

    /**
     * Сохранить список объектов класса <code>Department</code>
     *
     * @param departmentList список объектов класса <code>Department</code> для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void saveAllDepartment(List<Department> departmentList) throws SaveObjectException {
        try {
            connection.setAutoCommit(false);
            for (Department department : departmentList) {
                saveDepartment(department);
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
     * Найти объект класса <code>Department</code> по id
     *
     * @param id id объекта класса <code>Department</code>
     * @return найденный объект класса <code>Department</code>
     */
    @Override
    public Optional<Department> findDepartmentById(long id) {
        Department department = new Department();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM DEPARTMENT WHERE ID=?");
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                department.newBuilder()
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
