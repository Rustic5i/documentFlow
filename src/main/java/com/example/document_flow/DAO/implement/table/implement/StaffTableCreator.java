package com.example.document_flow.DAO.implement.table.implement;

import com.example.document_flow.DAO.abstraction.TableCreator;
import com.example.document_flow.config.DataBase.implement.SessionManagerIml;
import com.example.document_flow.config.ReadFileSql;
import com.example.document_flow.exception.CreateTableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final SessionManagerIml SESSION_DERBY_DATA_BASE = SessionManagerIml.getInstance();

    private final ReadFileSql READ_FILE_SQL = new ReadFileSql();

    private final Logger LOGGER = LoggerFactory.getLogger(StaffTableCreator.class.getName());

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
     * Создает таблицы в базе данных
     * @throws CreateTableException в случае если создание таблиц(ы) в бд терпит не удачу
     */
    @Override
    public void creatTablesDB() throws CreateTableException {
        List<String> arraySqlScripts = getArraySqlScripts();
        try (Statement statement = SESSION_DERBY_DATA_BASE.getConnection().createStatement()) {
            for (String sqlScript : arraySqlScripts) {
                statement.executeUpdate(sqlScript);
            }
        } catch (SQLException e) {
            throw new CreateTableException("Ошибка при создание таблицы в бд"+e);
        }
    }

    /**
     * @return список SQL скриптов из файла import.sql
     */
    private List<String> getArraySqlScripts() {
        return READ_FILE_SQL.read();
    }
}
