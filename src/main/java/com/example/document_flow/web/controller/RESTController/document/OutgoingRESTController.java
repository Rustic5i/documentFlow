package com.example.document_flow.web.controller.RESTController.document;

import com.example.document_flow.entity.document.Outgoing;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.service.abstraction.document.OutgoingService;
import com.example.document_flow.service.implement.document.derby.OutgoingServiceDerby;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/outgoing")
public class OutgoingRESTController {

    private final OutgoingService outgoingService = OutgoingServiceDerby.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Outgoing> getAllOutgoing() {
        try {
            return outgoingService.getAll();
        } catch (GetDataObjectException e) {
            e.printStackTrace();
        }
        return null;
    }
}
