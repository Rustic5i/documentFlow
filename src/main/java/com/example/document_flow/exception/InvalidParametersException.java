package com.example.document_flow.exception;

/**
 * В случае если передали не валидные данные,
 * то необходимо выбрасывать исключение.
 * @author Баратов Руслан
 */
public class InvalidParametersException extends Exception {

    public InvalidParametersException(String message) {
        super(message);
    }
}
