package com.example.document_flow.factory.documentFactory;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.factory.abstr.DocumentFactory;
import com.example.document_flow.factory.generator.DataGenerator;

/**
 * Абстрактаня фабрика для создания различных типов документов
 *
 * @param <T> Тип документа
 * @author Баратов Руслан
 */
public abstract class AbstractDocumentFactory<T extends Document> implements DocumentFactory {

    private final DataGenerator dataGenerator = DataGenerator.getInstance();

    /**
     * Реализация фабричного метода
     *
     * @return возращает новый инстанс документа с заполнеными поля
     */
    public T create() {
        T document = createInstance();
        document.setName(dataGenerator.getName());
        document.setText(dataGenerator.getText());
        document.setAuthor(dataGenerator.getAuthor());
        document.setDateRegistration(dataGenerator.getDateRegistration());
        document.setRegistrationNumber(dataGenerator.getRegistrationNumber());
        fillAdditionalFields(document);
        return document;
    }

    /**
     * Фабричный метод по созданию инстансов документов
     *
     * @return документ
     */
    abstract T createInstance();

    /**
     * Метод по заполения дополнительных полей документа
     *
     * @param document
     */
    abstract void fillAdditionalFields(T document);

    DataGenerator getDataGenerator() {
        return dataGenerator;
    }

}
