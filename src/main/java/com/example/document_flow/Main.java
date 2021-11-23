package com.example.document_flow;

import com.example.document_flow.controller.RequestHandler;
import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.repository.RepositoryDocument;
import com.example.document_flow.util.read.DeserializationXML;
import com.example.document_flow.util.write.SerializableJSON;
import com.example.document_flow.util.write.SerializableXML;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        RequestHandler requestHandler = new RequestHandler();
        requestHandler.setRequest(args);

        //Сериализуем трех работников в xml
        SerializableXML xmlSerializable = new SerializableXML();
        Set<String> nameFills = xmlSerializable.serializable(DataGenerator.getInstance().personList.stream().limit(1).toList());

        //механизм загрузки оргштатных единиц из XML-документов.
        DeserializationXML jaxbRead = new DeserializationXML();
        List<Organization> list = jaxbRead.deserialization(nameFills, Organization.class);

        //Отчеты по сгенерированным документам выгружены в файлы в формате JSON
        RepositoryDocument repository = RepositoryDocument.getInstance();
        SerializableJSON parserJSON = new SerializableJSON();
        parserJSON.Serialization(repository.groupByAuthor());

        xmlSerializable.deleteXml("Person0.xml");
    }
}
