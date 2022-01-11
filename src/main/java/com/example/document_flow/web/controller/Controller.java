package com.example.document_flow.web.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;

@Path("/")
public class Controller {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getHtml() {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("templates/index.html")) {
            return inputStream;
        } catch (IOException e) {
            return null;
        }
    }
}
