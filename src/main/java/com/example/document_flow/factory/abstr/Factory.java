package com.example.document_flow.factory.abstr;

import com.example.document_flow.document.Document;
import com.example.document_flow.myException.DocumentExistsException;

public interface Factory {

    /**
     * Фабричный метод для создания объектов наследников класса <code>Document</code>
     * поля объекта заполняются случайными значениями
     *
     * @return document с заполненными, рандомными значениями
     * @throws DocumentExistsException если документ с генерируемым регистрационным номером уже существует
     */
    Document creatDocument() throws DocumentExistsException;
}
