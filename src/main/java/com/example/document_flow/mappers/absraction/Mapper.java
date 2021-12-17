package com.example.document_flow.mappers.absraction;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Интерфейс mapper, со списком методов для преобразования данных из различных объектов в объект <code>T</code>.
 * Type parameters: <T> – к чему маппить
 *
 * @author Баратов Руслан
 */
public interface Mapper<T> {

    /**
     * Преобразует данные <code>ResultSet</code>  в Entity-объект класса <code>T</code>
     *
     * @param resultSet от куда переносить данные.
     * @return объекты с заполненными полями
     * @throws SQLException если метка столбца недоступна;
     *                      если произошла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении
     */
    T convertFrom(ResultSet resultSet) throws SQLException;
}
