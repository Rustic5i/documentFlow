package com.example.document_flow.DAO.implement.table;

import com.example.document_flow.config.DataBase.implement.DataSourceManagerImpl;
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
 * указанные в папке /resources/table/
 *
 * @author Баратов Руслан
 */
public class TableCreator implements com.example.document_flow.DAO.abstraction.TableCreator {

    private static TableCreator staffDerbyTableCreator;

    private final DataSourceManagerImpl sessionDataBase = DataSourceManagerImpl.getInstance();

    private final ReadFileSql readFileSql = new ReadFileSql();

    private TableCreator() {
    }

    /**
     * @return синголтон объект
     */
    public static TableCreator getInstance() {
        if (staffDerbyTableCreator == null) {
            staffDerbyTableCreator = new TableCreator();
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
        try (Connection connection = sessionDataBase.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
                for (String sqlScript : getArraySqlScripts()) {
                    String nameTable = sqlScript.split("\s+|\\(")[2];
                    statement.executeUpdate(sqlScript);
//                    if (!checkPresenceTable(statement.getConnection(), nameTable)) {
//                        statement.executeUpdate(sqlScript);
//                    }
                }
        } catch (SQLException e) {
            throw new CreateTableException("Ошибка при создание таблицы в бд", e);
        } catch (IOException e) {
            throw new CreateTableException("Ошибка при создание таблицы в бд, не удалось получить sql скрипт", e);
        }
    }

    /**
     * @return список SQL скриптов.
     * @throws IOException При возникновении ошибки ввода-вывода
     */
    private List<String> getArraySqlScripts() throws IOException {
        return readFileSql.read();
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
