package com.example.document_flow.factory.factoryDocument;

import com.example.document_flow.entity.Document;
import com.example.document_flow.factory.generator.DataGenerator;

/**
 * Абстрактный класс по заполнению документа основными данными
 * @author Баратов Руслан
 */
abstract class MapperDocument<T extends Document> {

    private DataGenerator dataGenerator = DataGenerator.getInstance();

    /**
     * Заполняет переданный документ основанными данными.
     * Гарантирует что документ будет с уникальным регистрационным номером.
     * @param document который нужно заполнить основные данные, рандомными значениями
     * @return document с заполненными, рандомными значениями
     */
    protected void fillTheBasicData(T document) {
        document.setName(dataGenerator.getName());
        document.setText(dataGenerator.getText());
        document.setAuthor(dataGenerator.getAuthor());
        document.setDateRegistration(dataGenerator.getDateRegistration());
        document.setRegistrationNumber(dataGenerator.getRegistrationNumber());
    }

    public DataGenerator getDataGenerator() {
        return dataGenerator;
    }
}
