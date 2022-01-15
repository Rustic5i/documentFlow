package com.example.document_flow.web.controller.RESTController.document;

import com.example.document_flow.entity.document.Task;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.service.abstraction.document.TaskService;
import com.example.document_flow.service.implement.document.derby.TaskServiceDerby;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/task")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskRESTController {

    private final TaskService taskService = TaskServiceDerby.getInstance();

    @GET
    public List<Task> getAllTask() {
        try {
            return taskService.getAll();
        } catch (GetDataObjectException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/{id}")
    public Task getTaskById(@PathParam("id") long id) {
        try {
            return taskService.findById(id).get();
        } catch (GetDataObjectException e) {
            e.printStackTrace();
        }
        return null;
    }

    @DELETE
    @Path("/{id}")
    public void deleteTaskById(@PathParam("id") long id) {
        try {
            taskService.deleteById(id);
        } catch (DeleteObjectException e) {
            e.printStackTrace();
        }
    }

    @POST
    public void saveTask(Task task) {
        try {
            taskService.save(task);
        } catch (SaveObjectException e) {
            e.printStackTrace();
        }
    }
}
