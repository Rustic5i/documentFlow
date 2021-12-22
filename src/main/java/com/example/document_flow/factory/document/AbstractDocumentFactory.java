package com.example.document_flow.factory.document;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.factory.Factory;
import com.example.document_flow.factory.generator.DataGenerator;

/**
 * Абстрактная фабрика для создания различных типов документов
 *
 * @param <T> Тип документа
 * @author Баратов Руслан
 */
public abstract class AbstractDocumentFactory<T extends Document> implements Factory<Document> {

    private final DataGenerator dataGenerator = DataGenerator.getInstance();

    /**
     * Фабричный метод по созданию инстансов документов
     *
     * @return документ
     */
    abstract T createInstance();

    /**
     * Метод по заполения дополнительных полей документа
     *
     * @param document документ который нужно заполнить дополнительные поля
     */
    abstract void fillAdditionalFields(T document);

    /**
     * Реализация фабричного метода
     *
     * @return возвращает новый инстанс документа с заполненными поля
     */
    public T create() {
        T document = createInstance();
        document.setName(dataGenerator.getName());
        document.setText(dataGenerator.getText());
        document.setAuthor(dataGenerator.getPerson());
        document.setDateRegistration(dataGenerator.getDateRegistration());
        document.setRegistrationNumber(dataGenerator.getRegistrationNumber());
        fillAdditionalFields(document);
        return document;
    }

    DataGenerator getDataGenerator() {
        return dataGenerator;
    }
}
