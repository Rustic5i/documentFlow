package com.example.document_flow.factory.abstr;

import com.example.document_flow.document.Document;
import com.example.document_flow.myException.DocumentExistsException;

/**
 * Предаставляет фабричный метод для создания объектов наследников класса <code>Document</code>
 */
public interface Factory {

    /**
     *
     * @return document с заполненными, рандомными значениями
     * @throws DocumentExistsException если документ с генерируемым регистрационным номером уже существует
     */
    Document createDocument() throws DocumentExistsException;
}
