package com.example.document_flow.config.DataBase.abstraction;

import java.sql.Connection;

/**
 * Интерфейс определяющий соединение (сеанс) с определенной базой данных.
 *
 * @author Баратов Руслан
 */
public interface SessionDataBase {

    /**
     * @return соединение (сеанс) с определенной базой данных.
     */
    Connection getConnection();

    /**
     * Закрывает соединение(сеанс) к базе данных
     */
    void close();
}
