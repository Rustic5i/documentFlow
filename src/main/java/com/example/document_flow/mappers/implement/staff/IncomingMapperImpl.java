package com.example.document_flow.mappers.implement.staff;

import com.example.document_flow.entity.document.Incoming;
import com.example.document_flow.entity.staff.Person;
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
    public Incoming convertFrom(ResultSet rs) throws SQLException {
        return new Incoming().newBuilder()
                .setId(rs.getLong(1))
                .setAddressee(rs.getString(3))
                .setOutgoingNumber(rs.getLong(4))
                .setOutgoingRegistrationDate(rs.getDate(5))
                .setName(rs.getString(7))
                .setText(rs.getString(8))
                .setRegistrationNumber(rs.getLong(9))
                .setDateRegistration(rs.getDate(10))
                .setSource(new Person().newBuilder()
                        .setId(rs.getLong(12))
                        .setSurname(rs.getString(13))
                        .setName(rs.getString(14))
                        .setPatronymic(rs.getString(15))
                        .setPost(rs.getString(16))
                        .setDateOfBirth(rs.getDate(17))
                        .setPhoneNumber(rs.getInt(18))
                        .build())
                .setAuthor(new Person().newBuilder()
                        .setId(rs.getLong(19))
                        .setSurname(rs.getString(21))
                        .setName(rs.getString(22))
                        .setPatronymic(rs.getString(23))
                        .setPost(rs.getString(24))
                        .setDateOfBirth(rs.getDate(25))
                        .setPhoneNumber(rs.getInt(26))
                        .build())
                .build();
    }
}
