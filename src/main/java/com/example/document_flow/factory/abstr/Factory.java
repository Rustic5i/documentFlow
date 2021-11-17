package com.example.document_flow.factory.abstr;

import com.example.document_flow.entity.Document;

/**
 * Предаставляет фабричный метод для создания объектов наследников класса <code>Document</code>
 * @author Баратов Руслан
 */
public interface Factory {

    /**
     *
     * @return document с заполненными, рандомными значениями
     */
    Document createDocument();

}
