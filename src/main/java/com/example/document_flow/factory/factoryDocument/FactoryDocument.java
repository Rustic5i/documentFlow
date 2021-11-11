package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.document.Document;
import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.myException.DocumentExistsException;

import java.util.HashSet;
import java.util.Set;

abstract class FactoryDocument {

    private DataGenerator dataGenerator = DataGenerator.getInstance();

    private static Set<Long> registrationNumber = new HashSet<>();

    /**
     * Заполняет основные данные документа рандомными значениями
     *
     * @param document который нужно заполнить основные данные, рандомными значениями
     * @return document с заполненными, рандомными значениями
     * @throws DocumentExistsException если документ с генерируемым регистрационным номером уже существует
     */
    protected Document getRandomInstance(Document document) throws DocumentExistsException {
        document.setName(dataGenerator.getName());
        document.setText(dataGenerator.getText());
        document.setAuthor(dataGenerator.getAuthor());
        document.setDateRegistration(dataGenerator.getDateRegistration());
        document.setRegistrationNumber(dataGenerator.getRegistrationNumber());

        long regNumber = document.getRegistrationNumber();
        if (registrationNumber.contains(regNumber)) {
            throw new DocumentExistsException("Document с регистрационным номер " + regNumber + " уже существует ");
        }
        registrationNumber.add(regNumber);
        return document;
    }

    public DataGenerator getDataGenerator() {
        return dataGenerator;
    }
}
