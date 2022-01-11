package com.example.document_flow.DAO.implement.dao.document;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.config.DataBase.abstraction.DataSourceManager;
import com.example.document_flow.config.DataBase.implement.DataSourceManagerImpl;
import com.example.document_flow.entity.document.Outgoing;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.mappers.absraction.OutgoingMapper;
import com.example.document_flow.mappers.implement.OutgoingMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO слой отвечает за предоставления доступа к базе данных.
 * Содержит методы связанные с сущностью {@link Outgoing}
 *
 * @author Баратов Руслан
 */
public class OutgoingDAO implements DAOCrud<Outgoing> {

    private static final String SQL_DELETE_OUTGOING_BY_ID = "DELETE FROM APP.OUTGOING WHERE DOCUMENT_ID = ?";

    private static final String SQL_UPDATE_OUTGOING = "UPDATE APP.OUTGOING t SET t.ADDRESSEE = ?, t.DELIVERY_METHOD = ? WHERE t.DOCUMENT_ID = ?";

    private static final String SQL_GET_ALL = "SELECT * FROM OUTGOING " +
            "JOIN DOCUMENT D on OUTGOING.DOCUMENT_ID = D.ID " +
            "JOIN PERSON P on P.ID = D.AUTHOR";

    private static final String SQL_SAVE_ALL = "INSERT INTO APP.OUTGOING (DOCUMENT_ID, ADDRESSEE, DELIVERY_METHOD) VALUES (?, ?, ?)";

    private static final String SQL_FIND_OUTGOING_BY_ID = "SELECT * FROM OUTGOING\n" +
            "    JOIN DOCUMENT D on OUTGOING.DOCUMENT_ID = D.ID\n" +
            "            JOIN PERSON P on P.ID = D.AUTHOR WHERE D.ID=?";

    private static OutgoingDAO outgoingDAO;

    private final DataSourceManager sessionManager = DataSourceManagerImpl.getInstance();

    private final OutgoingMapper outgoingMapper = OutgoingMapperImpl.getInstance();

    private final DocumentDAO documentDAO = DocumentDAO.getInstance();

    private OutgoingDAO() {
    }

    /**
     * @return синголтон обьект
     */
    public static OutgoingDAO getInstance() {
        if (outgoingDAO == null) {
            outgoingDAO = new OutgoingDAO();
        }
        return outgoingDAO;
    }

    /**
     * Сохранить объект класса {@link Outgoing}
     *
     * @param object объект класса {@link Outgoing} для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void save(Outgoing object) throws SaveObjectException {
        saveAll(List.of(object));
    }

    /**
     * Удалить {@link Outgoing} по id
     *
     * @param id - id объекта
     * @throws DeleteObjectException когда удаление объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void deleteById(long id) throws DeleteObjectException {
        try (PreparedStatement preparedStatement = sessionManager.getDataSource().getConnection().prepareStatement(SQL_DELETE_OUTGOING_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteObjectException(MessageFormat.format("Ошибка удаление Outgoing c id {0}", id), e);
        }
    }

    /**
     * Обновить данные объекта {@link Outgoing}
     *
     * @param object объект с обновленными данными
     * @throws SaveObjectException когда изменение объекта терпит не удачу по какой-либо причине
     */
    @Override
    public void update(Outgoing object) throws SaveObjectException {
        try (Connection connection = sessionManager.getDataSource().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_OUTGOING)) {
                connection.setAutoCommit(false);
                preparedStatement.setString(1, object.getAddressee());
                preparedStatement.setString(2, object.getDeliveryMethod());
                preparedStatement.setLong(3, object.getId());
                documentDAO.update(object);
                preparedStatement.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            throw new SaveObjectException(MessageFormat.format("Ошибка при обновления объекта Department c id {0}", object.getId()), e);
        }
    }

    /**
     * Получить список всех сохраненных объектов класса {@link Outgoing}
     *
     * @return список сохраненных объектов класса {@link Outgoing}
     * @throws GetDataObjectException когда получение данных терпит неудачу по какой-либо причине.
     */
    @Override
    public List<Outgoing> getAll() throws GetDataObjectException {
        List<Outgoing> outgoingList = new ArrayList<>();
        try (ResultSet rs = sessionManager.getDataSource().getConnection().prepareStatement(SQL_GET_ALL).executeQuery()) {
            while (rs.next()) {
                outgoingList.add(outgoingMapper.convertFrom(rs));
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
        }
        return outgoingList;
    }

    /**
     * Сохранить список объектов класса {@link Outgoing}
     *
     * @param objectList список объектов класса {@link Outgoing} для сохранения
     * @throws SaveObjectException когда сохранение объекта терпит неудачу по какой-либо причине
     */
    @Override
    public void saveAll(List<Outgoing> objectList) throws SaveObjectException {
        try (Connection connection = sessionManager.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ALL)) {
                for (Outgoing outgoing : objectList) {
                    preparedStatement.setLong(1, outgoing.getId());
                    preparedStatement.setString(2, outgoing.getAddressee());
                    preparedStatement.setString(3, outgoing.getDeliveryMethod());
                    documentDAO.save(outgoing);
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                throw new SaveObjectException("Ошибка сохранения объекта Outgoing ", e);
            }
        } catch (SQLException e) {
            throw new SaveObjectException("Ошибка сохранения объекта Outgoing ", e);
        }
    }

    /**
     * Найти объект класса {@link Outgoing} по id
     *
     * @param id id объекта класса {@link Outgoing}
     * @return найденный объект класса {@link Outgoing}
     * @throws GetDataObjectException когда получение данных терпит неудачу по какой-либо причине.
     */
    @Override
    public Optional<Outgoing> findById(long id) throws GetDataObjectException {
        Outgoing outgoing = new Outgoing();
        try (PreparedStatement preparedStatement = sessionManager.getDataSource().getConnection().prepareStatement(SQL_FIND_OUTGOING_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    outgoing = outgoingMapper.convertFrom(rs);
                }
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
        }
        return Optional.ofNullable(outgoing);
    }
}
