package com.example.document_flow.mappers.implement;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.mappers.absraction.PersonMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс mapper, со списком методов для преобразования данных из различных объектов в объект <code>Person</code>.
 *
 * @author Баратов Руслан
 */
public class PersonMapperImpl implements PersonMapper {

    private static final String ID = "ID";

    private static final String SURNAME = "SURNAME";

    private static final String NAME = "NAME";

    private static final String PATRONYMIC = "PATRONYMIC";

    private static final String POST = "POST";

    private static final String DATA_OF_BIRTH = "DATA_OF_BIRTH";

    private static final String PHONE_NUMBER = "PHONE_NUMBER";

    private static final String FULL_NAME = "FULL_NAME";

    private static final String SHORT_NAME = "SHORT_NAME";

    private static final String CONTACT_PHONE_NUMBER = "CONTACT_PHONE_NUMBER";

    private final ResultSetMapper rsMapperPerson = new ResultSetMapper("PERSON");

    private final ResultSetMapper rsMapperDepartment = new ResultSetMapper("DEPARTMENT");

    private static PersonMapperImpl personMapper;

    private PersonMapperImpl() {
    }

    /**
     * @return синголтон обьект
     */
    public static PersonMapperImpl getInstance() {
        if (personMapper == null) {
            personMapper = new PersonMapperImpl();
        }
        return personMapper;
    }

    /**
     * Преобразует данные {@link ResultSet} в Entity-объект класса {@link Person}
     *
     * @param resultSet от куда переносить данные
     * @return объекты с заполненными полями
     * @throws SQLException если метка столбца недоступна;
     *                      если произошла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении
     */
    @Override
    public Person convertFrom(ResultSet resultSet) throws SQLException {
        rsMapperPerson.setResultSet(resultSet);
        rsMapperDepartment.setResultSet(resultSet);
        return new Person().newBuilder()
                .setId(rsMapperPerson.getLong(ID))
                .setSurname(rsMapperPerson.getString(SURNAME))
                .setName(rsMapperPerson.getString(NAME))
                .setPatronymic(rsMapperPerson.getString(PATRONYMIC))
                .setPost(rsMapperPerson.getString(POST))
                .setDateOfBirth(rsMapperPerson.getDate(DATA_OF_BIRTH))
                .setPhoneNumber(rsMapperPerson.getInt(PHONE_NUMBER))
                .setDepartment(new Department().newBuilder()
                        .setId(rsMapperDepartment.getLong(ID))
                        .setFullName(rsMapperDepartment.getString(FULL_NAME))
                        .setShortName(rsMapperDepartment.getString(SHORT_NAME))
                        .setContactPhoneNumber(rsMapperDepartment.getString(CONTACT_PHONE_NUMBER))
                        .build())
                .build();
    }
}
