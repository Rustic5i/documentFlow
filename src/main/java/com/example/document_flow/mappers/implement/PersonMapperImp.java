package com.example.document_flow.mappers.implement;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.mappers.absraction.PersonMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс mapper, со списками методами преобразования данных из различных объектов в объект <code>Person</code>.
 *
 * @author Баратов Руслан
 */
public class PersonMapperImp implements PersonMapper {

    private static final String ID = "ID";

    private static final String SURNAME = "SURNAME";

    private static final String NAME = "NAME";

    private static final String PATRONYMIC = "PATRONYMIC";

    private static final String POST = "POST";

    private static final String DATA_OF_BIRTH = "DATA_OF_BIRTH";

    private static final String PHONE_NUMBER = "PHONE_NUMBER";

    private static PersonMapperImp personMapper;

    private PersonMapperImp() {
    }

    /**
     * @return синголтон обьект
     */
    public static PersonMapperImp getInstance() {
        if (personMapper == null) {
            personMapper = new PersonMapperImp();
        }
        return personMapper;
    }

    /**
     * Преобразует данные <code>ResultSet</code>  в Entity-объект класса <code>Person</code>
     *
     * @param resultSet от куда переносить данные
     * @return объекты с заполненными полями
     * @throws SQLException если метка столбца недоступна;
     *                      если произошла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении
     */
    @Override
    public Person convertFrom(ResultSet resultSet) throws SQLException {
        return new Person().newBuilder()
                .setId(resultSet.getLong(ID))
                .setSurname(resultSet.getString(SURNAME))
                .setName(resultSet.getString(NAME))
                .setPatronymic(resultSet.getString(PATRONYMIC))
                .setPost(resultSet.getString(POST))
                .setDateOfBirth(resultSet.getDate(DATA_OF_BIRTH))
                .setPhoneNumber(resultSet.getInt(PHONE_NUMBER))
                .build();
    }
}
