package com.example.document_flow.service.implement.document.derby;

import com.example.document_flow.DAO.abstraction.DAOCrud;
import com.example.document_flow.DAO.implement.dao.document.OutgoingDAO;
import com.example.document_flow.entity.document.Outgoing;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.service.abstraction.document.OutgoingService;

import java.util.List;
import java.util.Optional;

public class OutgoingServiceDerby implements OutgoingService {

    private static OutgoingServiceDerby outgoingServiceDerby;

    private final DAOCrud<Outgoing> outgoingDAO = OutgoingDAO.getInstance();

    private OutgoingServiceDerby() {
    }

    /**
     * @return синголтон обьект
     */
    public static OutgoingServiceDerby getInstance() {
        if (outgoingServiceDerby == null) {
            outgoingServiceDerby = new OutgoingServiceDerby();
        }
        return outgoingServiceDerby;
    }

    @Override
    public void save(Outgoing object) throws SaveObjectException {
        outgoingDAO.save(object);
    }

    @Override
    public void saveAll(List<Outgoing> objects) throws SaveObjectException {
        outgoingDAO.saveAll(objects);
    }

    @Override
    public List<Outgoing> getAll() throws GetDataObjectException {
        return outgoingDAO.getAll();
    }

    @Override
    public void deleteById(long id) throws DeleteObjectException {
        outgoingDAO.deleteById(id);
    }

    @Override
    public void update(Outgoing object) throws SaveObjectException {
        outgoingDAO.update(object);
    }

    @Override
    public Optional<Outgoing> findById(long id) throws GetDataObjectException {
        return outgoingDAO.findById(id);
    }
}
