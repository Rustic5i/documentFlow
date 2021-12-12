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

    private final DataGenerator DATA_GENERATOR = DataGenerator.getInstance();

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
        document.setName(DATA_GENERATOR.getName());
        document.setText(DATA_GENERATOR.getText());
        document.setAuthor(DATA_GENERATOR.getPerson());
        document.setDateRegistration(DATA_GENERATOR.getDateRegistration());
        document.setRegistrationNumber(DATA_GENERATOR.getRegistrationNumber());
        fillAdditionalFields(document);
        return document;
    }

    DataGenerator getDATA_GENERATOR() {
        return DATA_GENERATOR;
    }
}
