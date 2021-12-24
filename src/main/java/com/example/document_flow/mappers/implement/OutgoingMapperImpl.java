package com.example.document_flow.mappers.implement;

import com.example.document_flow.entity.document.Outgoing;
import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.mappers.absraction.OutgoingMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс mapper, со списком методов для преобразования данных из различных объектов в объект {@link Outgoing}
 *
 * @author Баратов Руслан
 */
public class OutgoingMapperImpl implements OutgoingMapper {

    private static final String NAME = "NAME";

    private static final String TEXT = "TEXT";

    private static final String REGISTRATION_NUMBER = "REGISTRATION_NUMBER";

    private static final String DATE_REGISTRATION = "DATE_REGISTRATION";

    private static final String DOCUMENT_ID = "DOCUMENT_ID";

    private static final String ADDRESSEE = "ADDRESSEE";

    private static final String DELIVERY_METHOD = "DELIVERY_METHOD";

    private static OutgoingMapperImpl outgoingMapper;

    private OutgoingMapperImpl() {
    }

    /**
     * @return синголтон объект
     */
    public static OutgoingMapperImpl getInstance() {
        if (outgoingMapper == null) {
            outgoingMapper = new OutgoingMapperImpl();
        }
        return outgoingMapper;
    }

    /**
     * Преобразует данные {@link ResultSet}  в Entity-объект класса {@link Outgoing}
     *
     * @param resultSet от куда переносить данные.
     * @return объекты с заполненными полями
     * @throws SQLException если метка столбца недоступна;
     *                      если произошла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении
     */
    @Override
    public Outgoing convertFrom(ResultSet resultSet) throws SQLException {
        return new Outgoing().newBuilder()
                .setId(resultSet.getLong(DOCUMENT_ID))
                .setName(resultSet.getString(NAME))
                .setText(resultSet.getString(TEXT))
                .setRegistrationNumber(resultSet.getLong(REGISTRATION_NUMBER))
                .setDateRegistration(resultSet.getDate(DATE_REGISTRATION))
                .setAddressee(resultSet.getString(ADDRESSEE))
                .setDeliveryMethod(resultSet.getString(DELIVERY_METHOD))
                .setAuthor(PersonMapperImpl.getInstance().convertFrom(resultSet))
                .build();
    }
}
