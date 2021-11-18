package com.example.document_flow.util;

import com.example.document_flow.entity.Document;
import com.example.document_flow.entity.person.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс для группирования документов по автору.
 * @author Баратов Руслан
 */
public class DocumentGroupService {

    private Map<Person, List<Document>> documentByAuthor = new HashMap<>();

    /**
     * Группирует переданный массив документов по автору
     *
     * @param documentList список документов
     * @return Map'у Документов сгруппированный по авторам.
     * Где ключ <code>Person</code> это автор.
     * Значение это список документов, созданный им.
     */

    public Map<Person, List<Document>> groupByAuthor(List<Document> documentList) {
        return documentByAuthor = documentList.stream().collect(Collectors.groupingBy(Document::getAuthor));
    }

    /**
     *
     * @return Возвращает строку, состоящий из значение полей документов, сгруппированные по автору
     *<pre>
     *Андреева Елизавета Павловна:
     *  * Входящее №15 от 3 марта 2017 Имя документа : Первый документ
     *  * Поручение №27 от 26 января 2017 Имя документа : Первый документ
     *Орлов Глеб Михайлович:
     *  * Поручение №19 от 24 января 2019 Имя документа : Третий документ
     *  * Поручение №83 от 12 февраля 2021 Имя документа : Второй документ
     *</pre>
     */
    public String groupByAuthorToString() {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<Person, List<Document>> entry : documentByAuthor.entrySet()) {
            str.append(entry.getKey()).append(":").append("\n");
            for (Document d : entry.getValue()) {
                str.append(" * ").append(d.toString()).append("\n");
            }
        }
        return str.toString();
    }
}
