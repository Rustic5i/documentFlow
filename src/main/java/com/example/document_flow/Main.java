package com.example.document_flow;

import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.web.controller.DocumentRequestHandler;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws SaveObjectException, IOException {
        DocumentRequestHandler requestHandler = new DocumentRequestHandler();
        requestHandler.setRequest(args);
    }

}
