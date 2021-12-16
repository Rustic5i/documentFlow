package com.example.document_flow.mappers.implement;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.mappers.absraction.DepartmentMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс mapper, со списком методов для преобразования данных из различных объектов в объект <code>Department</code>.
 *
 * @author Баратов Руслан
 */
public class DepartmentMapperImp implements DepartmentMapper {

    private static final String ID = "ID";

    private static final String SURNAME = "SURNAME";

    private static final String NAME = "NAME";

    private static final String PATRONYMIC = "PATRONYMIC";

    private static final String POST = "POST";

    private static final String DATA_OF_BIRTH = "DATA_OF_BIRTH";

    private static final String PHONE_NUMBER = "PHONE_NUMBER";

    private static final String FULL_NAME = "FULL_NAME";

    private static final String SHORT_NAME = "SHORT_NAME";

    private static final String MANAGER_ID = "MANAGER_ID";

    private static final String CONTACT_PHONE_NUMBER = "CONTACT_PHONE_NUMBER";

    private static DepartmentMapperImp departmentMapper;

    private DepartmentMapperImp() {
    }

    /**
     * @return синголтон обьект
     */
    public static DepartmentMapperImp getInstance() {
        if (departmentMapper == null) {
            departmentMapper = new DepartmentMapperImp();
        }
        return departmentMapper;
    }

    /**
     * Преобразует данные <code>ResultSet</code>  в Entity-объект класса <code>Department</code>
     *
     * @param resultSet от куда переносить данные.
     * @return объекты с заполненными полями
     * @throws SQLException если метка столбца недоступна;
     *                      если произошла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении
     */
    @Override
    public Department convertFrom(ResultSet resultSet) throws SQLException {
        return new Department().newBuilder()
                .setId(resultSet.getLong(ID))
                .setFullName(resultSet.getString(FULL_NAME))
                .setShortName(resultSet.getString(SHORT_NAME))
                .setManager(new Person().newBuilder()
                        .setId(resultSet.getLong(MANAGER_ID))
                        .setSurname(resultSet.getString(SURNAME))
                        .setName(resultSet.getString(NAME))
                        .setPatronymic(resultSet.getString(PATRONYMIC))
                        .setPost(resultSet.getString(POST))
                        .setDateOfBirth(resultSet.getDate(DATA_OF_BIRTH))
                        .setPhoneNumber(resultSet.getInt(PHONE_NUMBER))
                        .build())
                .setContactPhoneNumber(resultSet.getString(CONTACT_PHONE_NUMBER))
                .build();
    }
}
