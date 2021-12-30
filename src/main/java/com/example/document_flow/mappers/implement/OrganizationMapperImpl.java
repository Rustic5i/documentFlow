package com.example.document_flow.mappers.implement;

import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.mappers.absraction.OrganizationMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


/**
 * Класс mapper, со списком методов для преобразования данных из различных объектов в объект {@link Organization}
 *
 * @author Баратов Руслан
 */
public class OrganizationMapperImpl implements OrganizationMapper {

    private static final String ID = "ID";

    private static final String FULL_NAME = "FULL_NAME";

    private static final String SHORT_NAME = "SHORT_NAME";

    private static final String CONTACT_PHONE_NUMBER = "CONTACT_PHONE_NUMBER";

    private final ResultSetMapper resultSetMapper = new ResultSetMapper("ORGANIZATION");

    private static OrganizationMapperImpl organizationMapper;

    private OrganizationMapperImpl() {
    }

    /**
     * @return синголтон объект
     */
    public static OrganizationMapperImpl getInstance() {
        if (organizationMapper == null) {
            organizationMapper = new OrganizationMapperImpl();
        }
        return organizationMapper;
    }

    /**
     * Преобразует данные {@link ResultSet}  в Entity-объект класса <code>Organization</code>
     *
     * @param resultSet от куда переносить данные.
     * @return объекты с заполненными полями
     * @throws SQLException если метка столбца недоступна;
     *                      если произошла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении
     */
    @Override
    public Organization convertFrom(ResultSet resultSet) throws SQLException {
        resultSetMapper.setResultSet(resultSet);
        return new Organization().newBuilder()
                .setId(resultSetMapper.getLong(ID))
                .setFullName(resultSetMapper.getString(FULL_NAME))
                .setShortName(resultSetMapper.getString(SHORT_NAME))
                .setContactPhoneNumber(resultSetMapper.getString(CONTACT_PHONE_NUMBER))
                .setManager(PersonMapperImpl.getInstance().convertFrom(resultSet))
                .build();
    }
}
