package com.example.document_flow.service.implement.document.derby;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.DAO.implement.dao.document.TaskDAO;
import com.example.document_flow.entity.document.Task;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.service.abstraction.document.TaskService;

import java.util.List;
import java.util.Optional;

public class TaskServiceDerby implements TaskService {

    private static TaskServiceDerby taskServiceDerby;

    private final DAOCrud<Task> taskDAO = TaskDAO.getInstance();

    private TaskServiceDerby() {
    }

    /**
     * @return синголтон обьект
     */
    public static TaskServiceDerby getInstance() {
        if (taskServiceDerby == null) {
            taskServiceDerby = new TaskServiceDerby();
        }
        return taskServiceDerby;
    }

    @Override
    public void save(Task object) throws SaveObjectException {
        taskDAO.save(object);
    }

    @Override
    public void saveAll(List<Task> objects) throws SaveObjectException {
        taskDAO.saveAll(objects);
    }

    @Override
    public List<Task> getAll() throws GetDataObjectException {
        return taskDAO.getAll();
    }

    @Override
    public void deleteById(long id) throws DeleteObjectException {
        taskDAO.deleteById(id);
    }

    @Override
    public void update(Task object) throws SaveObjectException {
        taskDAO.update(object);
    }

    @Override
    public Optional<Task> findById(long id) throws GetDataObjectException {
        return taskDAO.findById(id);
    }
}
