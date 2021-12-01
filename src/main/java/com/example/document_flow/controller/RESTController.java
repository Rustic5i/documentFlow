package com.example.document_flow.controller;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.service.abstraction.AbstractDocumentService;
import com.example.document_flow.service.abstraction.AbstractService;
import com.example.document_flow.service.implement.DocumentService;
import com.example.document_flow.service.implement.PersonServiceXml;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Контроллер
 * Принимает HTTP-запросы
 *
 * @author Баратов Руслан
 */
@Path("/employees")
public class RESTController {

    private final AbstractDocumentService SERVICE = DocumentService.getInstance();

    private final AbstractService<Person> PERSON_SERVICE = PersonServiceXml.getInstance();

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
    public List<Document> getPerson(@PathParam("id") int id) {
        return SERVICE.getDocumentByIdAuthor(id);
    }

    /**
     * Принимает GET HTTP-запрос.
     *
     * @return перечень загруженных в приложение исполнителей,
     * в формате JSON
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPerson() {
        return PERSON_SERVICE.getAll();
    }
}
