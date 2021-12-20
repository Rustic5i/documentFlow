package com.example.document_flow.DAO.implement.table.implement;

import com.example.document_flow.DAO.abstraction.TableCreator;
import com.example.document_flow.config.DataBase.implement.ManagerDataSourceImpl;
import com.example.document_flow.config.ReadFileSql;
import com.example.document_flow.exception.CreateTableException;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс занимается созданием таблицы "DEPARTMENT" в базе данных,
 * по sql-скрипту указанный в файле import.sql в папке resources
 *
 * @author Баратов Руслан
 */
//public class DepartmentTableCreate implements TableCreator {
//
//    private static final String NAME_TABLE = "create table DEPARTMENT";
//
//    private static DepartmentTableCreate departmentTableCreate;
//
//    private final ManagerDataSourceImpl SESSION_DERBY_DATA_BASE = ManagerDataSourceImpl.getInstance();
//
//    private final ReadFileSql READ_FILE_SQL = new ReadFileSql();
//
//    private DepartmentTableCreate() {
//    }
//
//    /**
//     * @return синголтон объект
//     */
//    public static DepartmentTableCreate getInstance() {
//        if (departmentTableCreate == null) {
//            departmentTableCreate = new DepartmentTableCreate();
//        }
//        return departmentTableCreate;
//    }
//
//    /**
//     * Создает таблицы "DEPARTMENT" в базе данных
//     *
//     * @throws CreateTableException в случае если создание таблиц(ы) в бд терпит не удачу
//     */
//    @Override
//    public void createTablesDB() throws CreateTableException {
//        try (Statement statement = SESSION_DERBY_DATA_BASE.getDataSource().getConnection().createStatement()) {
//            try {
//                statement.executeUpdate(READ_FILE_SQL.getSqlScriptByKeywords(NAME_TABLE));
//            } catch (IOException e) {
//                throw new CreateTableException("Ошибка при создание таблицы в бд, не удалось получить sql скрипт из файла import.sql", e);
//            }
//        } catch (SQLException e) {
//            throw new CreateTableException("Ошибка при создание таблицы в бд", e);
//        }
//    }
//}
