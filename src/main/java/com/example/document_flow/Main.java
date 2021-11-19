package com.example.document_flow;

import com.example.document_flow.controller.RequestHandler;

public class Main {

    public static void main(String[] args) {
        RequestHandler requestHandler = new RequestHandler();
        requestHandler.setRequest(args);
    }
}
