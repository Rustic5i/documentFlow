package com.example.document_flow.web.controller.RESTController.staff;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.service.abstraction.staff.DepartmentService;
import com.example.document_flow.service.implement.staff.derby.DepartmentServiceDerby;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/api/organization/")
public class DepartmentRESTController {

    private final DepartmentService departmentService = DepartmentServiceDerby.getInstance();

    @GET
    @Path("{id}/department")
    public List<Department> getAllDepartmentByIdOrganization(@PathParam("id") long id) {
        try {
            return departmentService.findByIdOrganization(id);
        } catch (GetDataObjectException e) {
            e.printStackTrace();
        }
        return null;
    }
}
