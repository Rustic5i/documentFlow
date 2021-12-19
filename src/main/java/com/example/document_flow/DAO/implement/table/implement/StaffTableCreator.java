package com.example.document_flow.DAO.implement.table.implement;

import com.example.document_flow.DAO.abstraction.TableCreator;
import com.example.document_flow.config.DataBase.implement.ManagerDataSourceImpl;
import com.example.document_flow.config.ReadFileSql;
import com.example.document_flow.exception.CreateTableException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Класс занимается созданием нужных таблиц в базе данных Derby,
 * указанных в файле import.sql в папке resources
 *
 * @author Баратов Руслан
 */
public class StaffTableCreator implements TableCreator {

    private static StaffTableCreator staffDerbyTableCreator;

    private final ManagerDataSourceImpl SESSION_DERBY_DATA_BASE = ManagerDataSourceImpl.getInstance();

    private final ReadFileSql READ_FILE_SQL = new ReadFileSql();

    private StaffTableCreator() {
    }

    /**
     * @return синголтон объект
     */
    public static StaffTableCreator getInstance() {
        if (staffDerbyTableCreator == null) {
            staffDerbyTableCreator = new StaffTableCreator();
        }
        return staffDerbyTableCreator;
    }

    /**
     * Создает таблицы в базе данных если их нет.
     *
     * @throws CreateTableException в случае если создание таблиц(ы) в бд терпит не удачу
     */
    @Override
    public void createTablesDB() throws CreateTableException {
        try (Statement statement = SESSION_DERBY_DATA_BASE.getDataSource().getConnection().createStatement()) {
            try {
                for (String sqlScript : getArraySqlScripts()) {
                    String nameTable = sqlScript.split("\s+|\\(")[2];
                    if (!checkPresenceTable(statement.getConnection(), nameTable)) {
                        statement.executeUpdate(sqlScript);
                    }
                }
            } catch (IOException e) {
                throw new CreateTableException("Ошибка при создание таблицы в бд, не удалось получить sql скрипт из файла import.sql", e);
            }
        } catch (SQLException e) {
            throw new CreateTableException("Ошибка при создание таблицы в бд", e);
        }
    }

    /**
     * @return список SQL скриптов из файла import.sql.
     * @throws IOException При возникновении ошибки ввода-вывода
     */
    private List<String> getArraySqlScripts() throws IOException {
        return READ_FILE_SQL.read();
    }

    /**
     * Проверяет на наличие таблицы в бд
     *
     * @param connection соединение к бд
     * @param nameTable  имя таблицы
     * @return возвращает true если таблица с переданным именем уже существует в бд,
     * false если таблица с таким именем не было найдено.
     * @throws SQLException
     */
    private boolean checkPresenceTable(Connection connection, String nameTable) throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet resultSet = databaseMetaData
                .getTables(null, null, nameTable, new String[]{"TABLE"});
        return resultSet.next();
    }
}
