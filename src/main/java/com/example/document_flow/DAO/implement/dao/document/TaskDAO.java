package com.example.document_flow.DAO.implement.dao.document;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.config.DataBase.abstraction.DataSourceManager;
import com.example.document_flow.config.DataBase.implement.DataSourceManagerImpl;
import com.example.document_flow.entity.document.Task;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.mappers.absraction.TaskMapper;
import com.example.document_flow.mappers.implement.staff.TaskMapperImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO слой отвечает за предоставления доступа к базе данных.
 * Содержит методы связанные с сущностью {@link Task}
 *
 * @author Баратов Руслан
 */
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
            "VALUES (?, ?, ?, ?, ?,?)";

    private static final String SQL_FIND_TASK_BY_ID = "SELECT * FROM TASK T\n" +
            "       left JOIN DOCUMENT D on T.DOCUMENT_ID = D.ID\n" +
            "       left JOIN PERSON EXECUTOR on EXECUTOR.ID = T.RESPONSIBLE_EXECUTOR\n" +
            "       left JOIN PERSON CONTROLLER on CONTROLLER.ID = T.ORDER_CONTROLLER\n" +
            "       left JOIN PERSON AUTHOR on D.AUTHOR = AUTHOR.ID WHERE D.ID = ?";

    private static TaskDAO taskDAO;

    private final DataSourceManager sessionManager = DataSourceManagerImpl.getInstance();

    private final DocumentDAO documentDAO = DocumentDAO.getInstance();

    private final TaskMapper taskMapper = TaskMapperImpl.getInstance();

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

    /**
     * Сохранить объект класса {@link Task}
     *
     * @param object объект класса {@link Task} для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void save(Task object) throws SaveObjectException {
        saveAll(List.of(object));
    }

    /**
     * Удалить {@link Task} по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        try (Connection connection = sessionManager.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_TASK_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteObjectException(MessageFormat.format("Ошибка удаление Task c id {0}", id), e);
        }
    }

    /**
     * Обновить данные объекта {@link Task}
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(Task object) throws SaveObjectException {
        try (Connection connection = sessionManager
                .getDataSource().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_TASK)) {
                connection.setAutoCommit(false);
                preparedStatement.setDate(1, new Date(object.getDateOfIssue().getTime()));
                preparedStatement.setDate(2, new Date(object.getTermOfExecution().getTime()));
                preparedStatement.setLong(3, object.getResponsibleExecutor().getId());
                preparedStatement.setString(4, object.getSignOfControl());
                preparedStatement.setLong(5, object.getOrderController().getId());
                documentDAO.update(object);
                preparedStatement.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            throw new SaveObjectException(MessageFormat
                    .format("Ошибка при обновления объекта Task c id {0}", object.getId()), e);
        }
    }

    /**
     * Получить список всех сохраненных объектов класса {@link Task}
     *
     * @return список сохраненных объектов класса {@link Task}
     * @throws GetDataObjectException когда получение данных терпит неудачу по какой-либо причине.
     */
    @Override
    public List<Task> getAll() throws GetDataObjectException {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = sessionManager.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                tasks.add(taskMapper.convertFrom(rs));
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
        }
        return tasks;
    }

    /**
     * Сохранить список объектов класса {@link Task}
     *
     * @param objectList список объектов класса {@link Task} для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void saveAll(List<Task> objectList) throws SaveObjectException {
        try (Connection connection = sessionManager.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ALL)) {
                for (Task task : objectList) {
                    documentDAO.save(task);
                    preparedStatement.setLong(1, task.getId());
                    preparedStatement.setDate(2, new Date(task.getDateOfIssue().getTime()));
                    preparedStatement.setDate(3, new Date(task.getTermOfExecution().getTime()));
                    preparedStatement.setLong(4, task.getResponsibleExecutor().getId());
                    preparedStatement.setString(5, task.getSignOfControl());
                    preparedStatement.setLong(6, task.getOrderController().getId());
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

    /**
     * Найти объект класса {@link Task} по id
     *
     * @param id id объекта класса {@link Task}
     * @return найденный объект класса {@link Task}
     * @throws GetDataObjectException когда получение данных терпит неудачу по какой-либо причине.
     */
    @Override
    public Optional<Task> findById(long id) throws GetDataObjectException {
        Task task = new Task();
        try (Connection connection = sessionManager.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_TASK_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    task = taskMapper.convertFrom(rs);
                }
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
        }
        return Optional.ofNullable(task);
    }
}
