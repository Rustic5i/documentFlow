package com.example.document_flow.DAO.implement.table.implement;

import com.example.document_flow.DAO.abstraction.TableCreator;
import com.example.document_flow.config.DataBase.implement.SessionManagerImp;
import com.example.document_flow.config.ReadFileSql;
import com.example.document_flow.exception.CreateTableException;

import java.io.IOException;
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

    private final SessionManagerImp SESSION_DERBY_DATA_BASE = SessionManagerImp.getInstance();

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
     * Создает таблицы в базе данных
     *
     * @throws CreateTableException в случае если создание таблиц(ы) в бд терпит не удачу
     */
    @Override
    public void creatTablesDB() throws CreateTableException {
        try (Statement statement = SESSION_DERBY_DATA_BASE.getDataSource().getConnection().createStatement()) {
            try {
                for (String sqlScript : getArraySqlScripts()) {
                    statement.executeUpdate(sqlScript);
                }
            } catch (IOException e) {
                throw new CreateTableException("Ошибка при создание таблицы в бд, не удалось получить sql скрипт из файла import.sql" + e);
            }
        } catch (SQLException e) {
            throw new CreateTableException("Ошибка при создание таблицы в бд" + e);
        }
    }

    /**
     * @return список SQL скриптов из файла import.sql.
     * @throws IOException При возникновении ошибки ввода-вывода
     */
    private List<String> getArraySqlScripts() throws IOException {
        return READ_FILE_SQL.read();
    }
}
