package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.factory.generateDate.GenerateDataDocument;
import com.example.document_flow.model.Document;
import com.example.document_flow.myException.DocumentExistsException;


abstract class FactoryDocument {

    private static GenerateDataDocument generateDataDocument = new GenerateDataDocument();

    /**
     * Заполняет основные данные документа рандомными значениями
     * @param document который нужно заполнить основные данные, рандомными значениями
     * @return document с заполненными, рандомными значениями
     * @throws DocumentExistsException если документ с генерируемым регистрационным номером уже существует
     */
    protected static Document getRandomInstance(Document document) throws DocumentExistsException {
        document.setName(generateDataDocument.getName());
        document.setText(generateDataDocument.getText());
        document.setAuthor(generateDataDocument.getAuthor());
        document.setDateRegistration(generateDataDocument.getDateRegistration());
        document.setRegistrationNumber(generateDataDocument.getRandomRegNumber());
        return document;
    }
}
