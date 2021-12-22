package com.example.document_flow.web.controller.RESTController;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.service.abstraction.staff.PersonService;
import com.example.document_flow.service.implement.staff.derby.PersonServiceDerby;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/person")
public class PersonRESTController {

    private final PersonService PERSON_SERVICE = PersonServiceDerby.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPerson() {
        try {
            return PERSON_SERVICE.getAll();
        } catch (GetDataObjectException e) {
            e.printStackTrace();
        }
        return null;
    }
}
