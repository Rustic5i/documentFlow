package com.example.document_flow.DAO.implement.derby.table.implement;

import com.example.document_flow.DAO.abstraction.TableCreator;
import com.example.document_flow.config.DataBase.implement.SessionDerbyDataBase;
import com.example.document_flow.util.read.ReadFileSql;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Класс занимается созданием нужных таблиц в базе данных Derby,
 * указанных в файле import.sql в папке resources
 *
 * @author Баратов Руслан
 */
public class StaffDerbyTableCreator implements TableCreator {

    private final SessionDerbyDataBase SESSION_DERBY_DATA_BASE = SessionDerbyDataBase.getInstance();

    private final ReadFileSql READ_FILE_SQL = new ReadFileSql();

    private static StaffDerbyTableCreator staffDerbyTableCreator;

    private Logger LOGGER = LoggerFactory.getLogger(StaffDerbyTableCreator.class.getName());

    private StaffDerbyTableCreator() {
    }

    /**
     * Создает таблицы в базе данных
     */
    @Override
    public void creatTablesDB() {
        List<String> arraySqlScripts = getArraySqlScripts();
        try (Connection connection = SESSION_DERBY_DATA_BASE.getConnection();
             Statement statement = connection.createStatement()) {
            for (String sqlScript : arraySqlScripts) {
                statement.executeUpdate(sqlScript);
            }
        } catch (SQLException e) {
            LOGGER.error("Ошибка при создание таблицы в базе данной Derby", e);
        }
    }

    /**
     * @return список SQL скриптов из файла import.sql
     */
    private List<String> getArraySqlScripts() {
        return READ_FILE_SQL.read("import.sql");
    }

    /**
     * @return синголтон объект
     */
    public static StaffDerbyTableCreator getInstance() {
        if (staffDerbyTableCreator == null) {
            staffDerbyTableCreator = new StaffDerbyTableCreator();
        }
        return staffDerbyTableCreator;
    }
}
