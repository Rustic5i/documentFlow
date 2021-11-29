package com.example.document_flow.factory;

/**
 * Просто фабрика
 *
 * @author Баратов Руслан
 */
public interface Factory<T> {

    /**
     * Фабричный метод для создания различных объектов
     *
     * @return созданный обьект
     */
    T create();

    /**
     * @return Возвращает тип создаваемого обьекта
     */
    Class getTypeObject();
}
