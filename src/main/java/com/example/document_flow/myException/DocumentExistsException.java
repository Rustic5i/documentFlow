package com.example.document_flow.myException;

/**
 * В случае, если документ с генерируемым номером уже существует,
 * то необходимо выбрасывать исключение.
 *@author Баратов Руслан
 */
public class DocumentExistsException extends Exception{
    public DocumentExistsException(String message) {
        super(message);
    }
}
