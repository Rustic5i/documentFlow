package com.example.document_flow;

import com.example.document_flow.web.controller.DocumentRequestHandler;

public class Main {

    public static void main(String[] args) {
        DocumentRequestHandler requestHandler = new DocumentRequestHandler();
        requestHandler.setRequest(args);
    }
}
