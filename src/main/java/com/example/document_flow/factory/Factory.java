package com.example.document_flow.factory;

import java.util.List;

/**
 * Предаставляет фабричный метод для создания различных обьектов
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
     * @return Возвращает тип создаваемого обьекта
     */
    Class getTypeObject();
}
