package com.example.document_flow.web.observers;

import com.example.document_flow.DAO.implement.dao.document.DocumentDAO;
import com.example.document_flow.DAO.implement.dao.document.OutgoingDAO;
import com.example.document_flow.DAO.implement.dao.staff.OrganizationDAO;
import com.example.document_flow.entity.document.Document;
import com.example.document_flow.entity.document.DocumentType;
import com.example.document_flow.entity.document.Outgoing;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.GetDataObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.factory.Factory;
import com.example.document_flow.factory.document.OutgoingDocumentFactory;
import com.example.document_flow.factory.document.СreatorDocumentFactory;
import com.example.document_flow.service.abstraction.staff.DepartmentService;
import com.example.document_flow.service.abstraction.staff.OrganizationService;
import com.example.document_flow.service.abstraction.staff.PersonService;
import com.example.document_flow.service.implement.staff.derby.DepartmentServiceDerby;
import com.example.document_flow.service.implement.staff.derby.OrganizationServiceDerby;
import com.example.document_flow.service.implement.staff.derby.PersonServiceDerby;
import com.example.document_flow.service.implement.staff.xml.DepartmentServiceXmlImpl;
import com.example.document_flow.service.implement.staff.xml.OrganizationServiceXmlImpl;
import com.example.document_flow.service.implement.staff.xml.PersonServiceXmlImpl;
import com.example.document_flow.service.importing.DataImportingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Класс импортирует данные после старта приложения, из репозитория xml в базу данных Derby
 *
 * @author Баратов Руслан
 */
public class ImportDataObserver implements ServletContextListener {

    private final DepartmentService departmentServiceDerby = DepartmentServiceDerby.getInstance();

    private final OrganizationService organizationServiceDerby = OrganizationServiceDerby.getInstance();

    private final PersonService personServiceDerby = PersonServiceDerby.getInstance();

    private final DepartmentService departmentServiceXml = DepartmentServiceXmlImpl.getInstance();

    private final OrganizationService organizationServiceXml = OrganizationServiceXmlImpl.getInstance();

    private final PersonService personServiceXml = PersonServiceXmlImpl.getInstance();

    private final Logger logger = LoggerFactory.getLogger(ImportDataObserver.class.getName());

    /**
     * Импортирует данные из репозитория Xml в базу данных Derby
     *
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        try {
//            DataImportingService.importAll(personServiceXml, personServiceDerby);
//            DataImportingService.importAll(departmentServiceXml, departmentServiceDerby);
//            DataImportingService.importAll(organizationServiceXml, organizationServiceDerby);
//        } catch (SaveObjectException | GetDataObjectException e) {
//            logger.error("Ошибка при попытки импортирования данных", e);
//        }
        OutgoingDAO outgoingDAO = OutgoingDAO.getInstance();
        Factory<Document> factory = new СreatorDocumentFactory().createFactory(Outgoing.class);
        Outgoing outgoing = (Outgoing) factory.create();
        outgoing.setId(99L);
        try {
        //    outgoingDAO.save(outgoing);
//            outgoingDAO.getAll();
//            outgoing.setAddressee("ОБНОВА");
//            outgoingDAO.update(outgoing);
            outgoingDAO.findById(outgoing.getId());
            outgoingDAO.deleteById(outgoing.getId());
        } catch (GetDataObjectException | DeleteObjectException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
