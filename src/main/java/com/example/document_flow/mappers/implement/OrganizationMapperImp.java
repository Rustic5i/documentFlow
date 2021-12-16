package com.example.document_flow.mappers.implement;

import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.mappers.absraction.OrganizationMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Класс mapper, со списком методов для преобразования данных из различных объектов в объект <code>Organization</code>.
 *
 * @author Баратов Руслан
 */
public class OrganizationMapperImp implements OrganizationMapper {

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

    private static OrganizationMapperImp organizationMapper;

    private OrganizationMapperImp() {
    }

    /**
     * @return синголтон обьект
     */
    public static OrganizationMapperImp getInstance() {
        if (organizationMapper == null) {
            organizationMapper = new OrganizationMapperImp();
        }
        return organizationMapper;
    }

    /**
     * Преобразует данные <code>ResultSet</code>  в Entity-объект класса <code>Organization</code>
     *
     * @param resultSet от куда переносить данные.
     * @return объекты с заполненными полями
     * @throws SQLException если метка столбца недоступна;
     *                      если произошла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении
     */
    @Override
    public Organization convertFrom(ResultSet resultSet) throws SQLException {
        return new Organization().newBuilder()
                .setId(resultSet.getLong(ID))
                .setFullName(resultSet.getString(FULL_NAME))
                .setShortName(resultSet.getString(SHORT_NAME))
                .setContactPhoneNumber(resultSet.getString(CONTACT_PHONE_NUMBER))
                .setManager(new Person().newBuilder()
                        .setId(resultSet.getLong(MANAGER_ID))
                        .setSurname(resultSet.getString(SURNAME))
                        .setName(resultSet.getString(NAME))
                        .setPatronymic(resultSet.getString(PATRONYMIC))
                        .setPost(resultSet.getString(POST))
                        .setDateOfBirth(resultSet.getDate(DATA_OF_BIRTH))
                        .setPhoneNumber(resultSet.getInt(PHONE_NUMBER))
                        .build())
                .build();
    }
}
