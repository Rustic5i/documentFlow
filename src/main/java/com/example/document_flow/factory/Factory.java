package com.example.document_flow.factory;

import java.util.List;

/**
 * Предаставляет фабричный метод для создания документов
 *
 * @author Баратов Руслан
 */
public interface Factory<T> {

    /**
     * Фабричный метод для создания различных объектов
     * @return созданный обьект
     */
    T create();

    /**
     *
     * @param count количество создоваемых обьектов
     * @return Лист созданных обьектов
     */
    List<T> creatListObject(int count);

    /**
     * @return Возвращает тип создаваемого обьекта
     */
    Class getTypeObject();
}
