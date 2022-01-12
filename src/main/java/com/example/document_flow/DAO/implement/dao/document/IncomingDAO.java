package com.example.document_flow.DAO.implement.dao.document;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.config.DataBase.abstraction.DataSourceManager;
import com.example.document_flow.config.DataBase.implement.DataSourceManagerImpl;
import com.example.document_flow.entity.document.Incoming;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.mappers.absraction.IncomingMapper;
import com.example.document_flow.mappers.implement.staff.IncomingMapperImpl;

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
 * Содержит методы связанные с сущностью {@link Incoming}
 *
 * @author Баратов Руслан
 */
public class IncomingDAO implements DAOCrud<Incoming> {

    private static final String SQL_DELETE_INCOMING_BY_ID = "DELETE FROM APP.INCOMING WHERE DOCUMENT_ID = ?";

    private static final String SQL_UPDATE_INCOMING = "UPDATE APP.INCOMING t " +
            "SET t.SOURCE = ?, t.ADDRESSEE = ?,t.OUTGOING_NUMBER=? ,t.OUTGOING_REGISTRATION_DATE = ? " +
            "WHERE t.DOCUMENT_ID = ?";

    private static final String SQL_GET_ALL = "SELECT *\n" +
            "FROM INCOMING I\n" +
            "       left JOIN DOCUMENT D on I.DOCUMENT_ID = D.ID\n" +
            "       left JOIN PERSON SOURCE on SOURCE.ID = I.SOURCE\n" +
            "       left JOIN PERSON AUTHOR on D.AUTHOR = AUTHOR.ID";

    private static final String SQL_SAVE_ALL = "INSERT INTO APP.INCOMING " +
            "(DOCUMENT_ID, SOURCE, ADDRESSEE,OUTGOING_NUMBER,OUTGOING_REGISTRATION_DATE) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_FIND_INCOMING_BY_ID = "SELECT * FROM INCOMING\n" +
            "left JOIN DOCUMENT D on INCOMING.DOCUMENT_ID = D.ID\n" +
            "left JOIN PERSON SOURCE on SOURCE.ID = INCOMING.SOURCE " +
            "left JOIN PERSON AUTHOR on AUTHOR.ID = D.AUTHOR WHERE D.ID=?";

    private static IncomingDAO incomingDAO;

    private final DataSourceManager sessionManager = DataSourceManagerImpl.getInstance();

    private final DocumentDAO documentDAO = DocumentDAO.getInstance();

    private final IncomingMapper incomingMapper = IncomingMapperImpl.getInstance();

    private IncomingDAO() {
    }

    /**
     * @return синголтон обьект
     */
    public static IncomingDAO getInstance() {
        if (incomingDAO == null) {
            incomingDAO = new IncomingDAO();
        }
        return incomingDAO;
    }

    /**
     * Сохранить объект класса {@link Incoming}
     *
     * @param object объект класса {@link Incoming} для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void save(Incoming object) throws SaveObjectException {
        saveAll(List.of(object));
    }

    /**
     * Удалить {@link Incoming} по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        try (Connection connection = sessionManager.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_INCOMING_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteObjectException(MessageFormat.format("Ошибка удаление Incoming c id {0}", id), e);
        }
    }

    /**
     * Обновить данные объекта {@link Incoming}
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(Incoming object) throws SaveObjectException {
        try (Connection connection = sessionManager.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_INCOMING)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, object.getSource().getId());
            preparedStatement.setString(2, object.getAddressee());
            preparedStatement.setLong(3, object.getOutgoingNumber());
            preparedStatement.setDate(4, new Date(object.getOutgoingRegistrationDate().getTime()));
            preparedStatement.setLong(5, object.getId());
            documentDAO.update(object);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new SaveObjectException(MessageFormat
                    .format("Ошибка при обновления объекта Incoming c id {0}", object.getId()), e);
        }
    }

    /**
     * Получить список всех сохраненных объектов класса {@link Incoming}
     *
     * @return список сохраненных объектов класса {@link Incoming}
     * @throws GetDataObjectException когда получение данных терпит неудачу по какой-либо причине.
     */
    @Override
    public List<Incoming> getAll() throws GetDataObjectException {
        List<Incoming> incomingList = new ArrayList<>();
        try (Connection connection = sessionManager.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                incomingList.add(incomingMapper.convertFrom(rs));
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
        }
        return incomingList;
    }

    /**
     * Сохранить список объектов класса {@link Incoming}
     *
     * @param objectList список объектов класса {@link Incoming} для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void saveAll(List<Incoming> objectList) throws SaveObjectException {
        try (Connection connection = sessionManager.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ALL)) {
                for (Incoming incoming : objectList) {
                    documentDAO.save(incoming);
                    preparedStatement.setLong(1, incoming.getId());
                    preparedStatement.setLong(2, incoming.getSource().getId());
                    preparedStatement.setString(3, incoming.getAddressee());
                    preparedStatement.setLong(4, incoming.getOutgoingNumber());
                    preparedStatement.setDate(5, new Date(incoming.getOutgoingRegistrationDate().getTime()));
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                throw new SaveObjectException("Ошибка сохранения объекта Incoming ", e);
            }
        } catch (SQLException e) {
            throw new SaveObjectException("Ошибка сохранения объекта Incoming ", e);
        }
    }

    /**
     * Найти объект класса {@link Incoming} по id
     *
     * @param id id объекта класса {@link Incoming}
     * @return найденный объект класса {@link Incoming}
     * @throws GetDataObjectException когда получение данных терпит неудачу по какой-либо причине.
     */
    @Override
    public Optional<Incoming> findById(long id) throws GetDataObjectException {
        Incoming incoming = new Incoming();
        try (Connection connection = sessionManager.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_INCOMING_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    incoming = incomingMapper.convertFrom(rs);
                }
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
        }
        return Optional.ofNullable(incoming);
    }
}