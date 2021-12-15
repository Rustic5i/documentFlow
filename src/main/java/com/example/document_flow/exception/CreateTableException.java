package com.example.document_flow.exception;

/**
 * В случае, когда создание таблиц в бд терпит неудачу по какой-либо причине,
 * то необходимо выбрасывать исключение.
 *
 * @author Баратов Руслан
 */
public class CreateTableException extends Exception {
    public CreateTableException(String message) {
        super(message);
    }
}
