package com.example.document_flow.DAO.abstraction;

/**
 * Общий интерфейс, для классов специализирующих на созданий таблиц, в базе данных
 *
 * @author Баратов Руслан
 */
public interface TableCreator {

    /**
     * Создать таблицу в бд
     */
    void creatTablesDB();
}
