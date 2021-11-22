package com.example.document_flow;

import com.example.document_flow.controller.RequestHandler;
import com.example.document_flow.repository.RepositoryDocument;
import com.example.document_flow.util.read.SerializableJSON;
import com.example.document_flow.util.write.DeserializationXML;
import com.example.document_flow.util.read.SerializableXML;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws JAXBException, IOException {
        RequestHandler requestHandler = new RequestHandler();
        requestHandler.setRequest(args);

        //Сериализуем трех работников в xml
        SerializableXML xmlSerializable = new SerializableXML();
        Set<String> nameFills = xmlSerializable.serializableXmlStaff();

        //механизм загрузки оргштатных единиц из XML-документов.
        DeserializationXML jaxbRead = new DeserializationXML();
        List<Object> list = jaxbRead.deserializationFromXml(nameFills);

        //Отчеты по сгенерированным документам выгружены в файлы в формате JSON
        RepositoryDocument repository = RepositoryDocument.getInstance();
        SerializableJSON parserJSON = new SerializableJSON();
        parserJSON.Serialization(repository.groupByAuthor());

    }
}
