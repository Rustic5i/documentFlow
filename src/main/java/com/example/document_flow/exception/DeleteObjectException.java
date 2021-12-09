package com.example.document_flow.exception;

/**
 * В случае, когда удаление объекта терпит неудачу по какой-либо причине,
 * то необходимо выбрасывать исключение.
 *
 * @author Баратов Руслан
 */
public class DeleteObjectException extends Throwable {

    public DeleteObjectException(String message) {
        super(message);
    }
}
