package com.example.document_flow.entity.abstr;

/**
 *  Используется для сохранения документов
 *  @author Баратов Руслан
 */
public interface Storable {
    /**
     * Методы по получению идентификатора документа
     * @return идентификатора Документа
     */
    Long getIdDocument();

    /**
     * Метод по получению наименования хранилища(таблицы)
     * @return наименование хранилица(таблицы)
     */
    String getTableName();
}
