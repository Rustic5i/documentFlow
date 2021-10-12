package com.example.document_flow.myException;

public class DocumentExistsException extends Exception{
    public DocumentExistsException(String message) {
        super(message);
    }
}
