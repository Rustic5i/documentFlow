package com.example.document_flow;

import com.example.document_flow.controller.RequestHandler;
import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.factory.staff.AbstractStaffFactory;
import com.example.document_flow.factory.staff.OrganizationFactory;
import com.example.document_flow.repository.RepositoryDocument;
import com.example.document_flow.util.read.DeserializationXML;
import com.example.document_flow.util.write.SerializableJSON;
import com.example.document_flow.util.write.staff.PersonSerializableXML;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws JAXBException, IOException {
        RequestHandler requestHandler = new RequestHandler();
        requestHandler.setRequest(args);

        AbstractStaffFactory factory = new OrganizationFactory();

        //Сериализуем трех работников в xml
        PersonSerializableXML xmlSerializable = new PersonSerializableXML();
        Set<String> nameFills = xmlSerializable.serializableXmlStaff(factory.creatListObject(2));

        //механизм загрузки оргштатных единиц из XML-документов.
        DeserializationXML jaxbRead = new DeserializationXML();
        List<Organization> list = jaxbRead.deserializationFromXml(nameFills, Organization.class);

        //Отчеты по сгенерированным документам выгружены в файлы в формате JSON
        RepositoryDocument repository = RepositoryDocument.getInstance();
        SerializableJSON parserJSON = new SerializableJSON();
        parserJSON.Serialization(repository.groupByAuthor());

    }
}
