package com.example.document_flow.mappers.implement;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.mappers.absraction.DocumentMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentMapperIml implements DocumentMapper {

    private static final String ID = "ID";

    private static final String NAME = "NAME";

    private static final String TEXT = "TEXT";

    private static final String REGISTRATION_NUMBER = "REGISTRATION_NUMBER";

    private static final String DATE_REGISTRATION = "DATE_REGISTRATION";

    private static DocumentMapperIml documentMapperIml;

    private DocumentMapperIml() {
    }

    /**
     * @return синголтон объект
     */
    public static DocumentMapperIml getInstance() {
        if (documentMapperIml == null) {
            documentMapperIml = new DocumentMapperIml();
        }
        return documentMapperIml;
    }

    @Override
    public Document convertFrom(ResultSet resultSet) throws SQLException {
        Document document = new Document();
        document.setId(resultSet.getLong(ID));
        document.setName(resultSet.getString(NAME));
        document.setText(resultSet.getString(TEXT));
        document.setDateRegistration(resultSet.getDate(DATE_REGISTRATION));
        document.setRegistrationNumber(resultSet.getLong(REGISTRATION_NUMBER));
        document.setAuthor(PersonMapperImpl.getInstance().convertFrom(resultSet));
        return document;
    }
}
