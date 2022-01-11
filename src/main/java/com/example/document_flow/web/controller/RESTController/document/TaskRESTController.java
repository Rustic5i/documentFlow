package com.example.document_flow.web.controller.RESTController.document;

import com.example.document_flow.entity.document.Task;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.service.abstraction.document.TaskService;
import com.example.document_flow.service.implement.document.derby.TaskServiceDerby;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/task")
public class TaskRESTController {

    private final TaskService taskService = TaskServiceDerby.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> getAllTask() {
        try {
            return taskService.getAll();
        } catch (GetDataObjectException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Task getTaskById(@PathParam("id") long id) {
        try {
            return taskService.findById(id).get();
        } catch (GetDataObjectException e) {
            e.printStackTrace();
        }
        return null;
    }
}
