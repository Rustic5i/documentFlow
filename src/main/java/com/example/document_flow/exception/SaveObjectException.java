package com.example.document_flow.exception;

/**
 * В случае, когда сохранение объекта терпит неудачу по какой-либо причине
 * то необходимо выбрасывать исключение.
 *
 * @author Баратов Руслан
 */
public class SaveObjectException extends Exception {

    public SaveObjectException(String message) {
        super(message);
    }
}
