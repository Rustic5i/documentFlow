package com.example.document_flow;

import com.example.document_flow.DAO.implement.derby.PersonDerbyDataBase;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.web.controller.DocumentRequestHandler;

import java.sql.SQLException;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws SQLException, SaveObjectException {
        DocumentRequestHandler requestHandler = new DocumentRequestHandler();
        requestHandler.setRequest(args);

        PersonDerbyDataBase personDerbyDataBase = PersonDerbyDataBase.getInstance();
        personDerbyDataBase.findByIdd(1);

        personDerbyDataBase.updatee(new Person().newBuilder()
                .setName("dawdaw")
                .setSurname("scscscqaq")
                .setPatronymic("scasc")
                .setId(1)
                .setPhoneNumber(111)
                .setPost("sassa")
                .setDateOfBirth(new Date())
                .build());
    }
}
