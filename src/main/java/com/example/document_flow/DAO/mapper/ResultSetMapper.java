package com.example.document_flow.DAO.mapper;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.entity.staff.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс mapper, транспортирует данные из <code>ResultSet</code> в Entity-объект.
 *
 * @author Баратов Руслан
 */
public class ResultSetMapper {

    /**
     * Преобразует данные <code>ResultSet</code>  в Entity-объект класса <code>Person</code>
     *
     * @param rs от куда переносить данные
     * @return объекты с заполненными полями
     * @throws SQLException если метка столбца недоступна;
     *                      если произошла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении
     */
    public static Person mappingPerson(ResultSet rs) throws SQLException {
        return new Person().newBuilder()
                .setId(rs.getLong("ID"))
                .setSurname(rs.getString("SURNAME"))
                .setName(rs.getString("NAME"))
                .setPatronymic(rs.getString("PATRONYMIC"))
                .setPost(rs.getString("POST"))
                .setDateOfBirth(rs.getDate("DATA_OF_BIRTH"))
                .setPhoneNumber(rs.getInt("PHONE_NUMBER"))
                .build();
    }

    /**
     * Преобразует данные <code>ResultSet</code>  в Entity-объект класса <code>Organization</code>
     *
     * @param rs от куда переносить данные.
     * @return объекты с заполненными полями
     * @throws SQLException если метка столбца недоступна;
     *                      если произошла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении
     */
    public static Organization mappingOrganization(ResultSet rs) throws SQLException {
        return new Organization().newBuilder()
                .setId(rs.getLong("ID"))
                .setFullName(rs.getString("FULL_NAME"))
                .setShortName(rs.getString("SHORT_NAME"))
                .setContactPhoneNumber(rs.getString("CONTACT_PHONE_NUMBER"))
                .setManager(new Person().newBuilder()
                        .setId(rs.getLong("MANAGER_ID"))
                        .setSurname(rs.getString("SURNAME"))
                        .setName(rs.getString("NAME"))
                        .setPatronymic(rs.getString("PATRONYMIC"))
                        .setPost(rs.getString("POST"))
                        .setDateOfBirth(rs.getDate("DATA_OF_BIRTH"))
                        .setPhoneNumber(rs.getInt("PHONE_NUMBER"))
                        .build())
                .build();
    }

    /**
     * Преобразует данные <code>ResultSet</code>  в Entity-объект класса <code>Department</code>
     *
     * @param rs от куда переносить данные.
     * @return объекты с заполненными полями
     * @throws SQLException если метка столбца недоступна;
     *                      если произошла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении
     */
    public static Department mappingDepartment(ResultSet rs) throws SQLException {
        return new Department().newBuilder()
                .setId(rs.getLong("ID"))
                .setFullName(rs.getString("FULL_NAME"))
                .setShortName(rs.getString("SHORT_NAME"))
                .setManager(new Person().newBuilder()
                        .setId(rs.getLong("MANAGER_ID"))
                        .setSurname(rs.getString("SURNAME"))
                        .setName(rs.getString("NAME"))
                        .setPatronymic(rs.getString("PATRONYMIC"))
                        .setPost(rs.getString("POST"))
                        .setDateOfBirth(rs.getDate("DATA_OF_BIRTH"))
                        .setPhoneNumber(rs.getInt("PHONE_NUMBER"))
                        .build())
                .setContactPhoneNumber(rs.getString("CONTACT_PHONE_NUMBER"))
                .build();
    }
}
