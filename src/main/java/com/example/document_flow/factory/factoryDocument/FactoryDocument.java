package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.entity.Document;
import com.example.document_flow.factory.generator.DataGenerator;
import com.example.document_flow.myException.DocumentExistsException;

import java.util.HashSet;
import java.util.Set;

/**
 * Абстрактный класс по заполнению документа основными данными
 */
abstract class FactoryDocument {

    private DataGenerator dataGenerator = DataGenerator.getInstance();

    /**
     * Хранит список регистрационных номеров уже созданных документов
     */
    private static Set<Long> registrationNumber = new HashSet<>();

    /**
     * Заполняет переданный документ основанными данными.
     * Гарантирует что документ будет с уникальным регистрационным номером.
     * @param document который нужно заполнить основные данные, рандомными значениями
     * @return document с заполненными, рандомными значениями
     * @throws DocumentExistsException если документ с генерируемым регистрационным номером уже существует
     */
    protected Document fillTheBasicData(Document document) throws DocumentExistsException {
        long regNumber = dataGenerator.getRegistrationNumber();
        if (registrationNumber.contains(regNumber)) {
            throw new DocumentExistsException("Document с регистрационным номер " + regNumber + " уже существует ");
        }
        document.setName(dataGenerator.getName());
        document.setText(dataGenerator.getText());
        document.setAuthor(dataGenerator.getAuthor());
        document.setDateRegistration(dataGenerator.getDateRegistration());
        document.setRegistrationNumber(regNumber);
        registrationNumber.add(regNumber);
        return document;
    }

    public DataGenerator getDataGenerator() {
        return dataGenerator;
    }
}
