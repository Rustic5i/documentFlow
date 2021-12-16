package com.example.document_flow.mappers.absraction;

import com.example.document_flow.entity.staff.Organization;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Интерфейс mapper, со списком методов для преобразования данных из различных объектов в объект <code>Organization</code>.
 *
 * @author Баратов Руслан
 */
public interface OrganizationMapper {

    /**
     * Преобразует данные <code>ResultSet</code>  в Entity-объект класса <code>Organization</code>
     *
     * @param resultSet от куда переносить данные.
     * @return объекты с заполненными полями
     * @throws SQLException если метка столбца недоступна;
     *                      если произошла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении
     */
    Organization convertFrom(ResultSet resultSet) throws SQLException;

}
