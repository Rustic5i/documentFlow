package com.example.document_flow.mappers.implement.staff;

import com.example.document_flow.entity.document.Outgoing;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.mappers.absraction.OutgoingMapper;
import com.example.document_flow.mappers.implement.ResultSetMapper;

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

    private static final String ID = "ID";

    private static final String SURNAME = "SURNAME";

    private static final String PATRONYMIC = "PATRONYMIC";

    private static final String POST = "POST";

    private static final String DATA_OF_BIRTH = "DATA_OF_BIRTH";

    private static final String PHONE_NUMBER = "PHONE_NUMBER";

    private final ResultSetMapper rsMapperPerson = new ResultSetMapper("PERSON");

    private final ResultSetMapper rsMapperOutgoing = new ResultSetMapper("OUTGOING");

    private final ResultSetMapper rsMapperDocument = new ResultSetMapper("DOCUMENT");

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
        rsMapperPerson.setResultSet(resultSet);
        rsMapperOutgoing.setResultSet(resultSet);
        rsMapperDocument.setResultSet(resultSet);
        return new Outgoing().newBuilder()
                .setId(rsMapperOutgoing.getLong(DOCUMENT_ID))
                .setName(rsMapperDocument.getString(NAME))
                .setText(rsMapperDocument.getString(TEXT))
                .setRegistrationNumber(rsMapperDocument.getLong(REGISTRATION_NUMBER))
                .setDateRegistration(rsMapperDocument.getDate(DATE_REGISTRATION))
                .setAddressee(rsMapperOutgoing.getString(ADDRESSEE))
                .setDeliveryMethod(rsMapperOutgoing.getString(DELIVERY_METHOD))
                .setAuthor(new Person().newBuilder()
                        .setId(rsMapperPerson.getLong(ID))
                        .setSurname(rsMapperPerson.getString(SURNAME))
                        .setName(rsMapperPerson.getString(NAME))
                        .setPatronymic(rsMapperPerson.getString(PATRONYMIC))
                        .setPost(rsMapperPerson.getString(POST))
                        .setDateOfBirth(rsMapperPerson.getDate(DATA_OF_BIRTH))
                        .setPhoneNumber(rsMapperPerson.getInt(PHONE_NUMBER))
                        .build())
                .build();
    }
}
