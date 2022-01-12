package com.example.document_flow.web.controller.RESTController.document;

import com.example.document_flow.entity.document.Incoming;
import com.example.document_flow.entity.document.Task;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.service.abstraction.document.IncomingService;
import com.example.document_flow.service.implement.document.derby.IncomingServiceDerby;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/incoming")
public class IncomingRESTController {

    private final IncomingService incomingService = IncomingServiceDerby.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Incoming> getAllIncoming() {
        try {
            return incomingService.getAll();
        } catch (GetDataObjectException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Incoming getIncomingById(@PathParam("id") long id) {
        try {
            return incomingService.findById(id).get();
        } catch (GetDataObjectException e) {
            e.printStackTrace();
        }
        return null;
    }
}
