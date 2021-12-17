package com.example.document_flow.exception;

/**
 * В случае, если документ с генерируемым номером уже существует,
 * то необходимо выбрасывать исключение.
 *
 * @author Баратов Руслан
 */
public class DocumentExistsException extends RuntimeException {

    public DocumentExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentExistsException(String message) {
        super(message);
    }
}
