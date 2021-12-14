package com.example.document_flow.DAO.implement.derby.table.implement;

import com.example.document_flow.DAO.abstraction.TableCreator;
import com.example.document_flow.config.DataBase.implement.SessionManager;
import com.example.document_flow.config.ReadFileSql;
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
public class StaffDerbyTableCreator implements TableCreator {

    private static StaffDerbyTableCreator staffDerbyTableCreator;

    private final SessionManager SESSION_DERBY_DATA_BASE = SessionManager.getInstance();

    private final ReadFileSql READ_FILE_SQL = new ReadFileSql();

    private final Logger LOGGER = LoggerFactory.getLogger(StaffDerbyTableCreator.class.getName());

    private StaffDerbyTableCreator() {
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

    /**
     * Создает таблицы в базе данных
     */
    @Override
    public void creatTablesDB() {
        List<String> arraySqlScripts = getArraySqlScripts();
        try (Statement statement = SESSION_DERBY_DATA_BASE.getConnection().createStatement()) {
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
        return READ_FILE_SQL.read();
    }
}
