package com.example.document_flow.DAO.executor;

import com.example.document_flow.DAO.DTO.PreparedStatementDTO;
import com.example.document_flow.DAO.Function;
import com.example.document_flow.exception.SaveObjectException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class Executor<T> {

    public void executeInsert(Connection connection, String sql, List<Object> params) throws SQLException {
        try (var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                resultSet.next();
            }
        }
    }

    public Optional<T> executeSelect(Connection connection, String sql, long id,
                                     Function<ResultSet, T> rsHandler) throws SQLException {
        try (var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            try (var resultSet = preparedStatement.executeQuery()){
                return Optional.ofNullable(rsHandler.apply(resultSet));
            }
        }
    }

    public void executeInsert(PreparedStatement transfer,T dataObject) throws SaveObjectException {
        try(Connection connection = transfer.getConnection()) {
            try {
                transfer.executeUpdate();
            } catch (SQLException e) {
                connection.rollback();
                throw new SaveObjectException("Ошибка сохранения объекта Person " + e);
            }
        } catch (SQLException e) {
            throw new SaveObjectException("Ошибка сохранения объекта Person" + e);
        }
    }
}
