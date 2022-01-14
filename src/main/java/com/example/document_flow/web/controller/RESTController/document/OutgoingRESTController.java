package com.example.document_flow.web.controller.RESTController.document;

import com.example.document_flow.entity.document.Outgoing;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.service.abstraction.document.OutgoingService;
import com.example.document_flow.service.implement.document.derby.OutgoingServiceDerby;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/outgoing")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OutgoingRESTController {

    private final OutgoingService outgoingService = OutgoingServiceDerby.getInstance();

    @GET
    public List<Outgoing> getAllOutgoing() {
        try {
            return outgoingService.getAll();
        } catch (GetDataObjectException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/{id}")
    public Outgoing getOutgoingById(@PathParam("id") long id) {
        try {
            return outgoingService.findById(id).get();
        } catch (GetDataObjectException e) {
            e.printStackTrace();
        }
        return null;
    }

    @DELETE
    @Path("/{id}")
    public void deleteOutgoingById(@PathParam("id") long id) {
        try {
            outgoingService.deleteById(id);
        } catch (DeleteObjectException e) {
            e.printStackTrace();
        }
    }

    @POST
    public void saveOutgoing(Outgoing outgoing) {
        try {
            outgoingService.save(outgoing);
        } catch (SaveObjectException e) {
            e.printStackTrace();
        }
    }
}
