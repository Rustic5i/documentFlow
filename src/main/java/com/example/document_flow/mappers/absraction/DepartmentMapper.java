package com.example.document_flow.mappers.absraction;

import com.example.document_flow.entity.staff.Department;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Интерфейс mapper, со списками методами преобразования данных из различных объектов в объект <code>Department</code>.
 *
 * @author Баратов Руслан
 */
public interface DepartmentMapper {

    /**
     * Преобразует данные <code>ResultSet</code>  в Entity-объект класса <code>Department</code>
     *
     * @param resultSet от куда переносить данные.
     * @return объекты с заполненными полями
     * @throws SQLException если метка столбца недоступна;
     *                      если произошла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении
     */
    Department convertFrom(ResultSet resultSet) throws SQLException;
}
