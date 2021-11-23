package com.example.document_flow.factory.document;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.factory.Factory;
import com.example.document_flow.factory.generator.DataGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактаня фабрика для создания различных типов документов
 *
 * @param <T> Тип документа
 * @author Баратов Руслан
 */
public abstract class AbstractDocumentFactory<T extends Document> implements Factory<Document> {

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
        document.setAuthor(dataGenerator.getPerson());
        document.setDateRegistration(dataGenerator.getDateRegistration());
        document.setRegistrationNumber(dataGenerator.getRegistrationNumber());
        fillAdditionalFields(document);
        return document;
    }

    @Override
    public List<Document> creatListObject(int count) {
        List<Document> documentList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            documentList.add(create());
        }
        return documentList;
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
