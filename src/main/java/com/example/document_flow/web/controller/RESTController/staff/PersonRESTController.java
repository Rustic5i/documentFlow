package com.example.document_flow.web.controller.RESTController.staff;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.service.abstraction.staff.PersonService;
import com.example.document_flow.service.implement.staff.derby.PersonServiceDerby;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/organization/department")
public class PersonRESTController {

    private final PersonService personService = PersonServiceDerby.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPerson() {
        try {
            return personService.getAll();
        } catch (GetDataObjectException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/{IdDepartment}/person")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getListPersonByIdDepartment(@PathParam("IdDepartment") long id) {
        try {
            return personService.findByIdDepartment(id);
        } catch (GetDataObjectException e) {
            e.printStackTrace();
        }
        return null;
    }

}
