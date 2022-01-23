package com.example.document_flow.web.controller.RESTController.document;

import com.example.document_flow.entity.document.Incoming;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.service.abstraction.document.IncomingService;
import com.example.document_flow.service.implement.document.derby.IncomingServiceDerby;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/incoming")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IncomingRESTController {

    private final IncomingService incomingService = IncomingServiceDerby.getInstance();

    @GET
    public List<Incoming> getAllIncoming() {
        try {
            return incomingService.getAll();
        } catch (GetDataObjectException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/{id}")
    public Incoming getIncomingById(@PathParam("id") long id) {
        try {
            return incomingService.findById(id).get();
        } catch (GetDataObjectException e) {
            e.printStackTrace();
        }
        return null;
    }

    @DELETE
    @Path("/{id}")
    public void deleteIncomingById(@PathParam("id") long id) {
        try {
            incomingService.deleteById(id);
        } catch (DeleteObjectException e) {
            e.printStackTrace();
        }
    }

    @POST
    public void saveIncoming(Incoming incoming) {
        try {
            incomingService.save(incoming);
        } catch (SaveObjectException e) {
            e.printStackTrace();
        }
    }

    @PUT
    public void updateIncoming(Incoming incoming) {
        try {
            incomingService.update(incoming);
        } catch (SaveObjectException e) {
            e.printStackTrace();
        }
    }
}
