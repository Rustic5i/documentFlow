package com.example.document_flow.config.DataBase.abstraction;

import java.sql.Connection;

public interface SessionDataBase {

    Connection getConnection();
}
