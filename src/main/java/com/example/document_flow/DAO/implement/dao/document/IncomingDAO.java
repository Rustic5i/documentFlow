package com.example.document_flow.DAO.implement.dao.document;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.config.DataBase.abstraction.DataSourceManager;
import com.example.document_flow.config.DataBase.implement.DataSourceManagerImpl;
import com.example.document_flow.entity.document.Incoming;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.mappers.absraction.OutgoingMapper;
import com.example.document_flow.mappers.implement.OutgoingMapperImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

public class IncomingDAO implements DAOCrud<Incoming> {

    private static final String SQL_DELETE_INCOMING_BY_ID = "DELETE FROM APP.INCOMING WHERE DOCUMENT_ID = ?";

    private static final String SQL_UPDATE_INCOMING = "UPDATE APP.INCOMING t " +
            "SET t.SOURCE = ?, t.ADDRESSEE = ?,t.OUTGOING_NUMBER=? ,OUTGOING_REGISTRATION_DATE = ? WHERE t.DOCUMENT_ID = ?";

    private static final String SQL_GET_ALL = "SELECT * FROM INCOMING" +
            "JOIN DOCUMENT D on INCOMING.DOCUMENT_ID = D.ID " +
            "JOIN PERSON P on P.ID = D.AUTHOR";

    private static final String SQL_SAVE_ALL = "INSERT INTO APP.INCOMING (DOCUMENT_ID, ADDRESSEE, DELIVERY_METHOD) VALUES (?, ?, ?)";

    private static final String SQL_FIND_INCOMING_BY_ID = "SELECT * FROM INCOMING\n" +
            "    JOIN DOCUMENT D on INCOMING.DOCUMENT_ID = D.ID\n" +
            "            JOIN PERSON P on P.ID = D.AUTHOR WHERE D.ID=?";

    private static IncomingDAO incomingDAO;

    private final DataSourceManager sessionManager = DataSourceManagerImpl.getInstance();

    private final OutgoingMapper outgoingMapper = OutgoingMapperImpl.getInstance();

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
        try (PreparedStatement preparedStatement = sessionManager.getDataSource().getConnection().prepareStatement(SQL_DELETE_INCOMING_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteObjectException(MessageFormat.format("Ошибка удаление Incoming c id {0}", id), e);
        }
    }

    @Override
    public void update(Incoming object) throws SaveObjectException {
        try (PreparedStatement preparedStatement = sessionManager.getDataSource().getConnection().prepareStatement(SQL_UPDATE_INCOMING)) {
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
            throw new SaveObjectException(MessageFormat.format("Ошибка при обновления объекта Incoming c id {0}", object.getId()), e);
        }
    }

    @Override
    public List<Incoming> getAll() throws GetDataObjectException {
        return null;
    }

    @Override
    public void saveAll(List<Incoming> objectList) throws SaveObjectException {

    }

    @Override
    public Optional<Incoming> findById(long id) throws GetDataObjectException {
        return Optional.empty();
    }
}
