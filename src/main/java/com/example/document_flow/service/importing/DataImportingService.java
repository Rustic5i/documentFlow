package com.example.document_flow.service.importing;

import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.service.abstraction.Service;

/**
 * Сервисный класс отвечающий за импортирование данных из разных сервисов
 *
 * @author Баратов Руслан
 */
public class DataImportingService {

    /**
     * Импортирует все данные из одного репозитория/базы данных в другой репозиторий/базы данных
     *
     * @param fromWhere от-куда импортируем
     * @param where     куда импортируем
     * @throws SaveObjectException
     */
    public static void importAll(Service fromWhere, Service where) throws SaveObjectException {
        where.saveAll(fromWhere.getAll());
    }
}
