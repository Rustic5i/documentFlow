package com.example.document_flow.web.controller.RESTController.staff;

import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.service.abstraction.staff.OrganizationService;
import com.example.document_flow.service.implement.staff.derby.OrganizationServiceDerby;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/organization")
public class OrganizationRESTController {

    private final OrganizationService organizationService = OrganizationServiceDerby.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Organization> getAllOrganization() {
        try {
            return organizationService.getAll();
        } catch (GetDataObjectException e) {
            e.printStackTrace();
        }
        return null;
    }

}
