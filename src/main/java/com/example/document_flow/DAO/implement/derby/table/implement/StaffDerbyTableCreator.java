package com.example.document_flow.DAO.implement.derby.table.implement;

import com.example.document_flow.DAO.abstraction.TableCreator;
import com.example.document_flow.config.DataBase.implement.SessionDerbyDataBase;
import com.example.document_flow.util.read.ReadFileSql;

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

    private Connection connection;

    private final SessionDerbyDataBase SESSION_DERBY_DATA_BASE = SessionDerbyDataBase.getInstance();

    private final ReadFileSql READ_FILE_SQL = new ReadFileSql();

    private Statement statement;

    private static StaffDerbyTableCreator staffDerbyTableCreator;

    private StaffDerbyTableCreator() {
        connectToDB();
    }

    /***
     * Получение соединения (сеанса) к бд Derby
     */
    private void connectToDB() {
        try {
            this.connection = SESSION_DERBY_DATA_BASE.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Создает таблицы в базе данных
     */
    @Override
    public void creatTablesDB() {
        List<String> arraySqlScripts = getArraySqlScripts();

        for (String sqlScript : arraySqlScripts) {
            try {
                statement.executeUpdate(sqlScript);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
