package com.example.document_flow.mappers.implement;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetMapper {

    private ResultSet resultSet;

    private String nameTable;

    public ResultSetMapper(ResultSet resultSet, String nameTable) {
        this.resultSet = resultSet;
        this.nameTable = nameTable;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public ResultSetMapper(String nameTable) {
        this.nameTable = nameTable;
    }

    public String getString(String columnLabel) throws SQLException {
        return resultSet.getString(checkColumn(columnLabel));
    }

    public boolean getBoolean(String columnLabel) throws SQLException {
        return resultSet.getBoolean(checkColumn(columnLabel));
    }

    public byte getByte(String columnLabel) throws SQLException {
        return resultSet.getByte(checkColumn(columnLabel));
    }

    public short getShort(String columnLabel) throws SQLException {
        return resultSet.getShort(checkColumn(columnLabel));
    }

    public int getInt(String columnLabel) throws SQLException {
        return resultSet.getInt(checkColumn(columnLabel));
    }

    public long getLong(String columnLabel) throws SQLException {
        return resultSet.getLong(checkColumn(columnLabel));
    }

    public float getFloat(String columnLabel) throws SQLException {
        return resultSet.getFloat(checkColumn(columnLabel));
    }

    public double getDouble(String columnLabel) throws SQLException {
        return resultSet.getDouble(checkColumn(columnLabel));
    }

    public byte[] getBytes(String columnLabel) throws SQLException {
        return resultSet.getBytes(checkColumn(columnLabel));
    }

    public Date getDate(String columnLabel) throws SQLException {
        return resultSet.getDate(checkColumn(columnLabel));
    }

    private int checkColumn(String columnLabel) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
            if (resultSetMetaData.getTableName(i).equalsIgnoreCase(nameTable)
                    && resultSetMetaData.getColumnName(i).equalsIgnoreCase(columnLabel)) {
                return i;
            }
        }
        throw new SQLException(String.format("В ResultSet не было найдено столбца из таблицы {0} с именем {1}",
                nameTable, columnLabel));
    }
}
