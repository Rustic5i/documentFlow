package com.example.document_flow.mappers.implement;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.mappers.absraction.DepartmentMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс mapper, со списком методов для преобразования данных из различных объектов в объект <code>Department</code>.
 *
 * @author Баратов Руслан
 */
public class DepartmentMapperImpl implements DepartmentMapper {

    private static DepartmentMapperImpl departmentMapper;

    private DepartmentMapperImpl() {
    }

    /**
     * @return синголтон обьект
     */
    public static DepartmentMapperImpl getInstance() {
        if (departmentMapper == null) {
            departmentMapper = new DepartmentMapperImpl();
        }
        return departmentMapper;
    }

    /**
     * Преобразует данные <code>ResultSet</code>  в Entity-объект класса <code>Department</code>
     *
     * @param rs от куда переносить данные.
     * @return объекты с заполненными полями
     * @throws SQLException если метка столбца недоступна;
     *                      если произошла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении
     */
    @Override
    public Department convertFrom(ResultSet rs) throws SQLException {
        return new Department().newBuilder()
                .setId(rs.getLong(1))
                .setFullName(rs.getString(2))
                .setShortName(rs.getString(3))
                .setContactPhoneNumber(rs.getString(5))
                .setManager(new Person().newBuilder()
                        .setId(rs.getLong(7))
                        .setSurname(rs.getString(8))
                        .setName(rs.getString(9))
                        .setPatronymic(rs.getString(10))
                        .setPost(rs.getString(11))
                        .setDateOfBirth(rs.getDate(12))
                        .setPhoneNumber(rs.getInt(13))
                        .build())
                .SetOrganization(new Organization().newBuilder()
                        .setId(rs.getLong(14))
                        .setFullName(rs.getString(15))
                        .setShortName(rs.getString(16))
                        .setContactPhoneNumber(rs.getString(18))
                        .build())
                .build();
    }
}
