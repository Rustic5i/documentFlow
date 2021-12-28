package com.example.document_flow.mappers.implement;

import com.example.document_flow.entity.document.Incoming;
import com.example.document_flow.mappers.absraction.IncomingMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomingMapperImpl implements IncomingMapper {

    private static final String DOCUMENT_ID = "DOCUMENT_ID";

    private static final String ADDRESSEE = "ADDRESSEE";

    private static final String OUTGOING_NUMBER = "OUTGOING_NUMBER";

    private static final String OUTGOING_REGISTRATION_DATE = "OUTGOING_REGISTRATION_DATE";

    private static final String NAME = "NAME";

    private static final String TEXT = "TEXT";

    private static final String REGISTRATION_NUMBER = "REGISTRATION_NUMBER";

    private static final String DATE_REGISTRATION = "DATE_REGISTRATION";

    private static IncomingMapperImpl incomingMapper;

    private IncomingMapperImpl() {
    }

    /**
     * @return синголтон объект
     */
    public static IncomingMapperImpl getInstance() {
        if (incomingMapper == null) {
            incomingMapper = new IncomingMapperImpl();
        }
        return incomingMapper;
    }

    @Override
    public Incoming convertFrom(ResultSet resultSet) throws SQLException {
        return new Incoming().newBuilder()
                .setId(resultSet.getLong(DOCUMENT_ID))
                .setName(resultSet.getString(NAME))
                .setText(resultSet.getString(TEXT))
                .setDateRegistration(resultSet.getDate(DATE_REGISTRATION))
                .setRegistrationNumber(resultSet.getLong(REGISTRATION_NUMBER))
                .setAuthor(PersonMapperImpl.getInstance().convertFrom(resultSet))
                .setSource(PersonMapperImpl.getInstance().convertFrom(resultSet))
                .setAddressee(resultSet.getString(ADDRESSEE))
                .setOutgoingNumber(resultSet.getLong(OUTGOING_NUMBER))
                .setOutgoingRegistrationDate(resultSet.getDate(OUTGOING_REGISTRATION_DATE))
                .build();
    }
}
