package com.example.document_flow;

import com.example.document_flow.controller.RequestHandler;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.repository.RepositoryDocument;
import com.example.document_flow.util.readWrite.JSONSerializable;
import com.example.document_flow.util.readWrite.XMLDeserialization;
import com.example.document_flow.util.readWrite.XMLSerializable;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws JAXBException, IOException {
        RequestHandler requestHandler = new RequestHandler();
        requestHandler.setRequest(args);

        XMLSerializable xmlSerializable = new XMLSerializable();
        Set<String> nameFills = xmlSerializable.serializableXmlStaff();

        XMLDeserialization jaxbRead = new XMLDeserialization();
        List<Person> list = jaxbRead.deserializationFromXml(nameFills);

        for (Person person : list) {
            System.out.println(person);
        }

        RepositoryDocument repositoy = RepositoryDocument.getInstance();

        JSONSerializable parserJSON = new JSONSerializable();
        parserJSON.Serialization(repositoy.groupByAuthor());

    }
}
