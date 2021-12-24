package com.example.document_flow.DAO.implement.dao.document;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.config.DataBase.abstraction.DataSourceManager;
import com.example.document_flow.config.DataBase.implement.DataSourceManagerImpl;
import com.example.document_flow.entity.document.Document;
import com.example.document_flow.entity.document.Outgoing;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.mappers.absraction.DocumentMapper;
import com.example.document_flow.mappers.implement.DocumentMapperIml;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DocumentDAO implements DAOCrud<Document> {

    private static final String SQL_DELETE_DOCUMENT_BY_ID = "DELETE FROM APP.DOCUMENT WHERE ID = ?";

    private static final String SQL_UPDATE_DOCUMENT = "UPDATE APP.DOCUMENT doc set doc.NAME = ?, doc.TEXT = ?, doc.REGISTRATION_NUMBER = ?, doc.DATE_REGISTRATION = ?, doc.AUTHOR=? where doc.ID = ?";

    private static final String SQL_GET_ALL = "SELECT * FROM DOCUMENT JOIN PERSON P on DOCUMENT.AUTHOR = P.ID";

    private static final String SQL_SAVE_ALL = "INSERT INTO APP.DOCUMENT (ID, NAME, TEXT, REGISTRATION_NUMBER, DATE_REGISTRATION, AUTHOR) VALUES (?,?,?,?,?,?)";

    private static final String SQL_FIND_DOCUMENT_BY_ID = "SELECT * FROM DOCUMENT D JOIN PERSON P on P.ID = D.AUTHOR WHERE D.ID=?";

    private static DocumentDAO documentDAO;

    private final DataSourceManager sessionManager = DataSourceManagerImpl.getInstance();

    private final DocumentMapper documentMapper = DocumentMapperIml.getInstance();

    private DocumentDAO() {
    }

    /**
     * @return синголтон обьект
     */
    public static DocumentDAO getInstance() {
        if (documentDAO == null) {
            documentDAO = new DocumentDAO();
        }
        return documentDAO;
    }

    @Override
    public void save(Document object) throws SaveObjectException {
        saveAll(List.of(object));
    }

    @Override
    public void deleteById(long id) throws DeleteObjectException {
        try (PreparedStatement preparedStatement = sessionManager.getDataSource().getConnection().prepareStatement(SQL_DELETE_DOCUMENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteObjectException(MessageFormat.format("Ошибка удаление Document c id {0}", id), e);
        }
    }

    @Override
    public void update(Document object) throws SaveObjectException {
        try (PreparedStatement preparedStatement = sessionManager.getDataSource().getConnection().prepareStatement(SQL_UPDATE_DOCUMENT)) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getText());
            preparedStatement.setLong(3, object.getRegistrationNumber());
            preparedStatement.setDate(4, new Date(object.getDateRegistration().getTime()));
            preparedStatement.setLong(5, object.getAuthor().getId());
            preparedStatement.setLong(6, object.getId());
        } catch (SQLException e) {
            throw new SaveObjectException(MessageFormat.format("Ошибка при обновления объекта Document c id {0}", object.getId()), e);
        }
    }

    @Override
    public List<Document> getAll() throws GetDataObjectException {
        List<Document> documentList = new ArrayList<>();
        try (ResultSet rs = sessionManager.getDataSource().getConnection().prepareStatement(SQL_GET_ALL).executeQuery()) {
            while (rs.next()) {
                documentList.add(documentMapper.convertFrom(rs));
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
        }
        return documentList;
    }

    @Override
    public void saveAll(List<Document> objectList) throws SaveObjectException {
        try (Connection connection = sessionManager.getDataSource().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ALL)) {
                for (Document document : objectList) {
                    preparedStatement.setLong(1, document.getId());
                    preparedStatement.setString(2, document.getName());
                    preparedStatement.setString(3, document.getText());
                    preparedStatement.setLong(4, document.getRegistrationNumber());
                    preparedStatement.setDate(5, new Date(document.getDateRegistration().getTime()));
                    preparedStatement.setLong(6, document.getAuthor().getId());
                    preparedStatement.addBatch();
                }
            } catch (SQLException e) {
                throw new SaveObjectException("Ошибка сохранения объекта Document ", e);
            }
        } catch (SQLException e) {
            throw new SaveObjectException("Ошибка сохранения объекта Document ", e);
        }
    }

    @Override
    public Optional<Document> findById(long id) throws GetDataObjectException {
        Document document = new Document();
        try (PreparedStatement preparedStatement = sessionManager.getDataSource().getConnection().prepareStatement(SQL_FIND_DOCUMENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                document = documentMapper.convertFrom(rs);
            }
        } catch (SQLException e) {
            throw new GetDataObjectException("Ошибка при попытки получения данных ", e);
        }
        return Optional.of(document);
    }
}
