package com.example.document_flow.web.controller;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.service.abstraction.Service;
import com.example.document_flow.service.implement.staff.PersonServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Контроллер отвечает за обработку HTTP-вызовов.
 * Принимать запросы связанных с Работником <code>Person</code>
 * по url localhost:8080/ecm/employees
 *
 * @author Баратов Руслан
 */
@Path("/employees")
public class PersonController {

    private final Service<Person> PERSON_SERVICE = PersonServiceImpl.getInstance();

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
