package com.example.document_flow.mappers.absraction;

import com.example.document_flow.entity.staff.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Интерфейс mapper, со списком методов для преобразования данных из различных объектов в объект <code>Person</code>.
 *
 * @author Баратов Руслан
 */
public interface PersonMapper {

    /**
     * Преобразует данные <code>ResultSet</code>  в Entity-объект класса <code>Person</code>
     *
     * @param resultSet от куда переносить данные
     * @return объекты с заполненными полями
     * @throws SQLException если метка столбца недоступна;
     *                      если произошла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении
     */
    Person convertFrom(ResultSet resultSet) throws SQLException;
}
