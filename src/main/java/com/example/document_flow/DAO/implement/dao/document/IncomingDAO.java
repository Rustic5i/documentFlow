package com.example.document_flow.DAO.implement.dao.document;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.config.DataBase.abstraction.DataSourceManager;
import com.example.document_flow.config.DataBase.implement.DataSourceManagerImpl;
import com.example.document_flow.entity.document.Incoming;
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

public class IncomingDAO implements DAOCrud<Incoming> {

    private static final String SQL_DELETE_INCOMING_BY_ID = "DELETE FROM APP.INCOMING WHERE DOCUMENT_ID = ?";

    private static final String SQL_UPDATE_INCOMING = "UPDATE APP.INCOMING t " +
            "SET t.SOURCE = ?, t.ADDRESSEE = ?,t.OUTGOING_NUMBER=? ,t.OUTGOING_REGISTRATION_DATE = ? " +
            "WHERE t.DOCUMENT_ID = ?";

    private static final String SQL_GET_ALL = "SELECT *\n" +
            "FROM INCOMING I\n" +
            "         JOIN DOCUMENT D on I.DOCUMENT_ID = D.ID\n" +
            "         JOIN PERSON P on P.ID = I.SOURCE\n" +
            "         JOIN PERSON P2 on D.AUTHOR = P2.ID";

    private static final String SQL_SAVE_ALL = "INSERT INTO APP.INCOMING " +
            "(DOCUMENT_ID, SOURCE, ADDRESSEE,OUTGOING_NUMBER,OUTGOING_REGISTRATION_DATE) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_FIND_INCOMING_BY_ID = "SELECT * FROM INCOMING\n" +
            "JOIN DOCUMENT D on INCOMING.DOCUMENT_ID = D.ID\n" +
            "JOIN PERSON P on P.ID = INCOMING.SOURCE "+
            "JOIN PERSON P on P.ID = D.AUTHOR WHERE D.ID=?";

    private static IncomingDAO incomingDAO;

    private final DataSourceManager sessionManager = DataSourceManagerImpl.getInstance();

    private final DocumentDAO documentDAO = DocumentDAO.getInstance();

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

    @Override
    public void save(Incoming object) throws SaveObjectException {
        saveAll(List.of(object));
    }

    @Override
    public void deleteById(long id) throws DeleteObjectException {
        try (PreparedStatement preparedStatement = sessionManager
                .getDataSource().getConnection().prepareStatement(SQL_DELETE_INCOMING_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteObjectException(MessageFormat.format("Ошибка удаление Incoming c id {0}", id), e);
        }
    }

    @Override
    public void update(Incoming object) throws SaveObjectException {
        try (PreparedStatement preparedStatement = sessionManager
                .getDataSource().getConnection().prepareStatement(SQL_UPDATE_INCOMING)) {
            Connection connection = preparedStatement.getConnection();
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

    @Override
    public List<Incoming> getAll() throws GetDataObjectException {
        List<Incoming> incomingList = new ArrayList<>();
        try (ResultSet rs = sessionManager.getDataSource().getConnection().prepareStatement(SQL_GET_ALL).executeQuery()) {
            while (rs.next()) {
                incomingList.add(new Incoming().newBuilder()
                        .setId(rs.getLong(1))
                        .setAddressee(rs.getString(3))
                        .setOutgoingNumber(rs.getLong(4))
                        .setOutgoingRegistrationDate(rs.getDate(5))
                        .setName(rs.getString(7))
                        .setText(rs.getString(8))
                        .setRegistrationNumber(rs.getLong(9))
                        .setDateRegistration(rs.getDate(10))
                        .setSource(new Person().newBuilder()
                                .setId(rs.getLong(12))
                                .setSurname(rs.getString(13))
                                .setName(rs.getString(14))
                                .setPatronymic(rs.getString(15))
                                .setPost(rs.getString(16))
                                .setDateOfBirth(rs.getDate(17))
                                .setPhoneNumber(rs.getInt(18))
                                .build())
                        .setAuthor(new Person().newBuilder()
                                .setId(rs.getLong(19))
                                .setSurname(rs.getString(20))
                                .setName(rs.getString(21))
                                .setPatronymic(rs.getString(22))
                                .setPost(rs.getString(23))
                                .setDateOfBirth(rs.getDate(24))
                                .setPhoneNumber(rs.getInt(25))
                                .build())
                        .build());
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
        }
        return incomingList;
    }

    @Override
    public void saveAll(List<Incoming> objectList) throws SaveObjectException {
        try (Connection connection = sessionManager.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ALL)) {
                for (Incoming incoming : objectList) {
                    preparedStatement.setLong(1, incoming.getId());
                    preparedStatement.setLong(2, incoming.getSource().getId());
                    preparedStatement.setString(3, incoming.getAddressee());
                    preparedStatement.setLong(4, incoming.getOutgoingNumber());
                    preparedStatement.setDate(5, new Date(incoming.getOutgoingRegistrationDate().getTime()));
                    documentDAO.save(incoming);
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

    @Override
    public Optional<Incoming> findById(long id) throws GetDataObjectException {
        Incoming incoming = new Incoming();
        try (PreparedStatement preparedStatement = sessionManager.getDataSource()
                .getConnection().prepareStatement(SQL_FIND_INCOMING_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                incoming = new Incoming().newBuilder()
                        .setId(rs.getLong(1))
                        .setAddressee(rs.getString(3))
                        .setOutgoingNumber(rs.getLong(4))
                        .setOutgoingRegistrationDate(rs.getDate(5))
                        .setName(rs.getString(7))
                        .setText(rs.getString(8))
                        .setRegistrationNumber(rs.getLong(9))
                        .setDateRegistration(rs.getDate(10))
                        .setSource(new Person().newBuilder()
                                .setId(rs.getLong(12))
                                .setSurname(rs.getString(13))
                                .setName(rs.getString(14))
                                .setPatronymic(rs.getString(15))
                                .setPost(rs.getString(16))
                                .setDateOfBirth(rs.getDate(17))
                                .setPhoneNumber(rs.getInt(18))
                                .build())
                        .setAuthor(new Person().newBuilder()
                                .setId(rs.getLong(19))
                                .setSurname(rs.getString(20))
                                .setName(rs.getString(21))
                                .setPatronymic(rs.getString(22))
                                .setPost(rs.getString(23))
                                .setDateOfBirth(rs.getDate(24))
                                .setPhoneNumber(rs.getInt(25))
                                .build())
                        .build();
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
        }
        return Optional.of(incoming);
    }
}
