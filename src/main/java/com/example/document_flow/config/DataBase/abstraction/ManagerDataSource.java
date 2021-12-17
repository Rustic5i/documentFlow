package com.example.document_flow.config.DataBase.abstraction;

import javax.sql.DataSource;

/**
 * Интерфейс определяющий соединение (сеанс) с определенной базой данных.
 *
 * @author Баратов Руслан
 */
public interface ManagerDataSource {

    /**
     * @return Объект, реализующий интерфейс источника данных. Объект источника данных,
     * являющийся альтернативой средству DriverManager,
     * является предпочтительным средством установления соединения.
     */
    DataSource getDataSource();
}
