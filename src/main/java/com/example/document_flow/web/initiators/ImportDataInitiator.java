package com.example.document_flow.web.initiators;

import com.example.document_flow.exception.SaveObjectException;
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

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Класс импортирует данные после старта приложения, из репозитория xml в базу данных Derby
 *
 * @author Баратов Руслан
 */
//@WebListener
public class ImportDataInitiator implements ServletContextListener {

    private DepartmentService departmentServiceDerby = DepartmentServiceDerby.getInstance();

    private OrganizationService organizationServiceDerby = OrganizationServiceDerby.getInstance();

    private PersonService personServiceDerby = PersonServiceDerby.getInstance();

    private DepartmentService departmentServiceXml = DepartmentServiceXmlImpl.getInstance();

    private OrganizationService organizationServiceXml = OrganizationServiceXmlImpl.getInstance();

    private PersonService personServiceXml = PersonServiceXmlImpl.getInstance();

    /**
     * Импортирует данные из репозитория Xml в базу данных Derby
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            DataImportingService.importAll(departmentServiceXml, departmentServiceDerby);
            DataImportingService.importAll(organizationServiceXml, organizationServiceDerby);
            DataImportingService.importAll(personServiceXml, personServiceDerby);
        } catch (SaveObjectException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}