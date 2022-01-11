package com.example.document_flow.service.implement.document.derby;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.DAO.implement.dao.document.IncomingDAO;
import com.example.document_flow.entity.document.Incoming;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.service.abstraction.document.IncomingService;

import java.util.List;
import java.util.Optional;

public class IncomingServiceDerby implements IncomingService {

    private static IncomingServiceDerby incomingServiceDerby;

    private final DAOCrud<Incoming> incomingDAO = IncomingDAO.getInstance();

    private IncomingServiceDerby() {
    }

    /**
     * @return синголтон обьект
     */
    public static IncomingServiceDerby getInstance() {
        if (incomingServiceDerby == null) {
            incomingServiceDerby = new IncomingServiceDerby();
        }
        return incomingServiceDerby;
    }

    @Override
    public void save(Incoming object) throws SaveObjectException {
        incomingDAO.save(object);
    }

    @Override
    public void saveAll(List<Incoming> objects) throws SaveObjectException {
        incomingDAO.saveAll(objects);
    }

    @Override
    public List<Incoming> getAll() throws GetDataObjectException {
        return incomingDAO.getAll();
    }

    @Override
    public void deleteById(long id) throws DeleteObjectException {
        incomingDAO.deleteById(id);
    }

    @Override
    public void update(Incoming object) throws SaveObjectException {
        incomingDAO.update(object);
    }

    @Override
    public Optional<Incoming> findById(long id) throws GetDataObjectException {
        return incomingDAO.findById(id);
    }
}
