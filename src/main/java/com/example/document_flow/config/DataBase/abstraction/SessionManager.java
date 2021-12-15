package com.example.document_flow.config.DataBase.abstraction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Интерфейс определяющий соединение (сеанс) с определенной базой данных.
 *
 * @author Баратов Руслан
 */
public interface SessionManager {

    /**
     * @return соединение (сеанс) с определенной базой данных.
     */
    Connection getConnection() throws SQLException;
}
