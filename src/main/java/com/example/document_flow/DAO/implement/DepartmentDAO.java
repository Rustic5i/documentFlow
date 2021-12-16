package com.example.document_flow.DAO.implement;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.config.DataBase.abstraction.SessionManager;
import com.example.document_flow.config.DataBase.implement.SessionManagerImp;
import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.mappers.absraction.DepartmentMapper;
import com.example.document_flow.mappers.implement.DepartmentMapperImp;

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
public class DepartmentDAO implements DAOCrud<Department> {

    private static final String SQL_DELETE_DEPARTMENT_BY_ID = "DELETE FROM APP.DEPARTMENT WHERE ID = ?";

    private static final String SQL_UPDATE_DEPARTMENT = "UPDATE APP.DEPARTMENT t SET t.FULL_NAME = ?, t.SHORT_NAME = ?, t.CONTACT_PHONE_NUMBER = ?,t.MANAGER_ID=? WHERE t.ID = ?";

    private static final String SQL_GET_ALL = "SELECT * FROM DEPARTMENT";

    private static final String SQL_SAVE_ALL = "INSERT INTO APP.DEPARTMENT (FULL_NAME, SHORT_NAME, MANAGER_ID, CONTACT_PHONE_NUMBER, ID)\n" +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_FIND_DEPARTMENT_BY_ID = "SELECT * FROM DEPARTMENT " +
            "JOIN PERSON P on P.ID = DEPARTMENT.MANAGER_ID where DEPARTMENT.ID =?";

    private static DepartmentDAO derbyDataBase;

    private final SessionManager SESSION_MANAGER = SessionManagerImp.getInstance();

    private final DepartmentMapper DEPARTMENT_MAPPER = DepartmentMapperImp.getInstance();

    private DepartmentDAO() {
    }

    /**
     * @return синголтон обьект
     */
    public static DepartmentDAO getInstance() {
        if (derbyDataBase == null) {
            derbyDataBase = new DepartmentDAO();
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
        try (PreparedStatement preparedStatement = SESSION_MANAGER.getDataSource().getConnection().prepareStatement(SQL_DELETE_DEPARTMENT_BY_ID)) {
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
        try (PreparedStatement preparedStatement = SESSION_MANAGER.getDataSource().getConnection().prepareStatement(SQL_UPDATE_DEPARTMENT)) {
            preparedStatement.setString(1, object.getFullName());
            preparedStatement.setString(2, object.getShortName());
            preparedStatement.setLong(3, object.getManager().getId());
            preparedStatement.setString(4, object.getContactPhoneNumber());
            preparedStatement.setLong(5, object.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SaveObjectException("Ошибка при обновления объекта Department c id " + object.getId());
        }
    }

    /**
     * Получить список всех сохраненных объектов класса <code>Department</code>
     *
     * @return список сохраненных объектов класса <code>Department</code>
     * @throws GetDataObjectException когда получение данных терпит неудачу по какой-либо причине.
     */
    @Override
    public List<Department> getAll() throws GetDataObjectException {
        List<Department> departmentList = new ArrayList<>();
        try (ResultSet rs = SESSION_MANAGER.getDataSource().getConnection().prepareStatement(SQL_GET_ALL).executeQuery()) {
            while (rs.next()) {
                departmentList.add(DEPARTMENT_MAPPER.convertFrom(rs));
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных " + e);
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
        try (Connection connection = SESSION_MANAGER.getDataSource().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ALL)) {
                connection.setAutoCommit(false);
                for (Department department : departmentList) {
                    preparedStatement.setString(1, department.getFullName());
                    preparedStatement.setString(2, department.getShortName());
                    preparedStatement.setLong(3, department.getManager().getId());
                    preparedStatement.setString(4, department.getContactPhoneNumber());
                    preparedStatement.setLong(5, department.getId());
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
     * @throws GetDataObjectException когда получение данных терпит неудачу по какой-либо причине.
     */
    @Override
    public Optional<Department> findById(long id) throws GetDataObjectException {
        Department department = new Department();
        try (PreparedStatement preparedStatement = SESSION_MANAGER.getDataSource().getConnection().prepareStatement(SQL_FIND_DEPARTMENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery();) {
                while (rs.next()) {
                    department = DEPARTMENT_MAPPER.convertFrom(rs);
                }
            } catch (SQLException e) {
                throw new GetDataObjectException("Ошибка при попытки получения данных " + e);
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных " + e);
        }
        return Optional.of(department);
    }
}
