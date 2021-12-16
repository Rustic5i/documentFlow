package com.example.document_flow.exception;

/**
 * В случае, когда получение данных терпит неудачу по какой-либо причине,
 * то необходимо выбрасывать исключение.
 *
 * @author Баратов Руслан
 */
public class GetDataObjectException extends Exception {
    public GetDataObjectException(String message) {
        super(message);
    }
}
