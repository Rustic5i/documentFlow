package com.example.document_flow.factory.abstr;

import com.example.document_flow.model.Document;

public interface Factory {

    /**
     * Метод для создания документов. Создает и заполняет документ рандомными значениями.
     *
     * @return Document с заполненными, рандомными значениями
     */
    Document creatDocument();
}
