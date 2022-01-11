package com.example.document_flow;

import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.web.controller.DocumentRequestHandler;

public class Main {

    public static void main(String[] args) throws SaveObjectException {
        DocumentRequestHandler requestHandler = new DocumentRequestHandler();
        requestHandler.setRequest(args);
    }
}
