package com.example.document_flow.web.controller;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.service.abstraction.document.DocumentService;
import com.example.document_flow.service.implement.document.DocumentServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Контроллер отвечает за обработку HTTP-вызовов.
 * Принимать запросы связанных с Документами <code>Document</code>
 * по url localhost:8080/ecm/employees/*
 *
 * @author Баратов Руслан
 */
@Path("/employees")
public class DocumentController {

    private final DocumentService documentService = DocumentServiceImpl.getInstance();

    /**
     * Принимает GET HTTP-запрос.
     * Выполняет поиск документов по id автора
     *
     * @param id id работника
     * @return перечень документов, созданных автором с указанным id в формате XML
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Document> getAllDocumentByAuthor(@PathParam("id") long id) {
        return documentService.getDocumentByIdAuthor(id);
    }
}
