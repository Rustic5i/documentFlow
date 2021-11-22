package com.example.document_flow.factory.document;

import com.example.document_flow.entity.document.Document;

/**
 * Предаставляет фабричный метод для создания документов
 *
 * @author Баратов Руслан
 */
public interface DocumentFactory {

    /**
     * @return Фабричный метод для создания объектов наследников класса <code>Document</code>
     */
    Document create();

    /**
     * @return Возвращает тип создаваемого документа
     */
    Class getDocumentType();
}
