package com.example.document_flow.DAO.abstraction;

import com.example.document_flow.exception.CreateTableException;

/**
 * Общий интерфейс, для классов специализирующих на созданий таблиц, в базе данных
 *
 * @author Баратов Руслан
 */
public interface TableCreator {

    /**
     * Создать таблицу в бд
     *
     * @throws CreateTableException в случае если создание таблиц(ы) в бд терпит не удачу
     */
    void createTablesDB() throws CreateTableException;
}
