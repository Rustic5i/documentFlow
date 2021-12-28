package com.example.document_flow.DAO.implement.dao.document;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.config.DataBase.abstraction.DataSourceManager;
import com.example.document_flow.config.DataBase.implement.DataSourceManagerImpl;
import com.example.document_flow.entity.document.Task;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDAO implements DAOCrud<Task> {

    private static final String SQL_DELETE_TASK_BY_ID = "DELETE FROM APP.TASK WHERE DOCUMENT_ID = ?";

    private static final String SQL_UPDATE_TASK = "UPDATE APP.TASK t " +
            "SET t.DATE_OF_ISSUE = ?, t.TERM_OF_EXECUTION = ?," +
            "t.RESPONSIBLE_EXECUTOR = ?, t.SIGN_OF_CONTROL=?, t.ORDER_CONTROLLER = ?" +
            "WHERE t.DOCUMENT_ID = ?";

    private static final String SQL_GET_ALL = "SELECT *   FROM TASK T\n" +
            "                     JOIN DOCUMENT D on T.DOCUMENT_ID = D.ID\n" +
            "                     JOIN PERSON P on P.ID = T.RESPONSIBLE_EXECUTOR\n" +
            "                     JOIN PERSON P2 on P2.ID = T.ORDER_CONTROLLER\n" +
            "                     JOIN PERSON P3 on D.AUTHOR = P3.ID";

    private static final String SQL_SAVE_ALL = "INSERT INTO APP.TASK " +
            "(DOCUMENT_ID, DATE_OF_ISSUE, TERM_OF_EXECUTION, RESPONSIBLE_EXECUTOR, SIGN_OF_CONTROL, ORDER_CONTROLLER) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_FIND_TASK_BY_ID = "SELECT *   FROM TASK T\n" +
            "                     JOIN DOCUMENT D on T.DOCUMENT_ID = D.ID\n" +
            "                     JOIN PERSON P on P.ID = T.RESPONSIBLE_EXECUTOR\n" +
            "                     JOIN PERSON P2 on P2.ID = T.ORDER_CONTROLLER\n" +
            "                     JOIN PERSON P3 on D.AUTHOR = P3.ID WHERE D.ID=?";

    private static TaskDAO taskDAO;

    private final DataSourceManager sessionManager = DataSourceManagerImpl.getInstance();

    private final DocumentDAO documentDAO = DocumentDAO.getInstance();

    private TaskDAO() {
    }

    /**
     * @return синголтон обьект
     */
    public static TaskDAO getInstance() {
        if (taskDAO == null) {
            taskDAO = new TaskDAO();
        }
        return taskDAO;
    }

    @Override
    public void save(Task object) throws SaveObjectException {
        saveAll(List.of(object));
    }

    @Override
    public void deleteById(long id) throws DeleteObjectException {
        try (PreparedStatement preparedStatement = sessionManager
                .getDataSource().getConnection().prepareStatement(SQL_DELETE_TASK_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteObjectException(MessageFormat.format("Ошибка удаление Task c id {0}", id), e);
        }
    }

    @Override
    public void update(Task object) throws SaveObjectException {
        try (PreparedStatement preparedStatement = sessionManager
                .getDataSource().getConnection().prepareStatement(SQL_UPDATE_TASK)) {
            Connection connection = preparedStatement.getConnection();
            connection.setAutoCommit(false);
            preparedStatement.setDate(1, new Date(object.getDateOfIssue().getTime()));
            preparedStatement.setDate(2, new Date(object.getTermOfExecution().getTime()));
            preparedStatement.setLong(3, object.getResponsibleExecutor().getId());
            preparedStatement.setString(4, object.getSignOfControl());
            preparedStatement.setLong(5, object.getOrderController().getId());
            documentDAO.update(object);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new SaveObjectException(MessageFormat
                    .format("Ошибка при обновления объекта Task c id {0}", object.getId()), e);
        }
    }

    @Override
    public List<Task> getAll() throws GetDataObjectException {
        List<Task> tasks = new ArrayList<>();
        try (ResultSet rs = sessionManager.getDataSource()
                .getConnection().prepareStatement(SQL_GET_ALL).executeQuery()) {
            while (rs.next()) {
                tasks.add(new Task().newBuilder()
                        .setDateOfIssue(rs.getDate(1))
                        .setTermOfExecution(rs.getDate(2))
                        .setSignOfControl(rs.getString(4))
                        .setName(rs.getString(5))
                        .setText(rs.getString(6))
                        .setRegistrationNumber(rs.getLong(7))
                        .setDateRegistration(rs.getDate(8))
                        .setResponsibleExecutor(new Person().newBuilder()
                                .setId(rs.getLong(13))
                                .setSurname(rs.getString(14))
                                .setName(rs.getString(15))
                                .setPatronymic(rs.getString(16))
                                .setPost(rs.getString(17))
                                .setDateOfBirth(rs.getDate(18))
                                .setPhoneNumber(rs.getInt(19))
                                .build())
                        .setOrderController(new Person().newBuilder()
                                .setId(rs.getLong(20))
                                .setSurname(rs.getString(21))
                                .setName(rs.getString(22))
                                .setPatronymic(rs.getString(23))
                                .setPost(rs.getString(24))
                                .setDateOfBirth(rs.getDate(25))
                                .setPhoneNumber(rs.getInt(26))
                                .build())
                        .setAuthor(new Person().newBuilder()
                                .setId(rs.getLong(27))
                                .setSurname(rs.getString(28))
                                .setName(rs.getString(29))
                                .setPatronymic(rs.getString(30))
                                .setPost(rs.getString(31))
                                .setDateOfBirth(rs.getDate(32))
                                .setPhoneNumber(rs.getInt(33))
                                .build())
                        .build());
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
        }
        return tasks;
    }

    @Override
    public void saveAll(List<Task> objectList) throws SaveObjectException {
        try (Connection connection = sessionManager.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ALL)) {
                for (Task task : objectList) {
                    preparedStatement.setLong(1, task.getId());
                    preparedStatement.setDate(2, new Date(task.getDateOfIssue().getTime()));
                    preparedStatement.setDate(3, new Date(task.getTermOfExecution().getTime()));
                    preparedStatement.setLong(4, task.getResponsibleExecutor().getId());
                    preparedStatement.setString(5, task.getSignOfControl());
                    preparedStatement.setLong(6, task.getOrderController().getId());
                    documentDAO.save(task);
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                throw new SaveObjectException("Ошибка сохранения объекта Task ", e);
            }
        } catch (SQLException e) {
            throw new SaveObjectException("Ошибка сохранения объекта Task ", e);
        }
    }

    @Override
    public Optional<Task> findById(long id) throws GetDataObjectException {
        Task task = new Task();
        try (PreparedStatement preparedStatement = sessionManager.getDataSource()
                .getConnection().prepareStatement(SQL_FIND_TASK_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                task = new Task().newBuilder()
                        .setDateOfIssue(rs.getDate(1))
                        .setTermOfExecution(rs.getDate(2))
                        .setSignOfControl(rs.getString(4))
                        .setName(rs.getString(5))
                        .setText(rs.getString(6))
                        .setRegistrationNumber(rs.getLong(7))
                        .setDateRegistration(rs.getDate(8))
                        .setResponsibleExecutor(new Person().newBuilder()
                                .setId(rs.getLong(13))
                                .setSurname(rs.getString(14))
                                .setName(rs.getString(15))
                                .setPatronymic(rs.getString(16))
                                .setPost(rs.getString(17))
                                .setDateOfBirth(rs.getDate(18))
                                .setPhoneNumber(rs.getInt(19))
                                .build())
                        .setOrderController(new Person().newBuilder()
                                .setId(rs.getLong(20))
                                .setSurname(rs.getString(21))
                                .setName(rs.getString(22))
                                .setPatronymic(rs.getString(23))
                                .setPost(rs.getString(24))
                                .setDateOfBirth(rs.getDate(25))
                                .setPhoneNumber(rs.getInt(26))
                                .build())
                        .setAuthor(new Person().newBuilder()
                                .setId(rs.getLong(27))
                                .setSurname(rs.getString(28))
                                .setName(rs.getString(29))
                                .setPatronymic(rs.getString(30))
                                .setPost(rs.getString(31))
                                .setDateOfBirth(rs.getDate(32))
                                .setPhoneNumber(rs.getInt(33))
                                .build())
                        .build();
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
        }
        return Optional.of(task);
    }
}
