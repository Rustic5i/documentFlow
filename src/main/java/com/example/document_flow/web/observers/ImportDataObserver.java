package com.example.document_flow.web.observers;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.exception.GetDataObjectException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Random;

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
        try {
            DataImportingService.importAll(personServiceXml, personServiceDerby);
            DataImportingService.importAll(organizationServiceXml, organizationServiceDerby);
            DataImportingService.importAll(departmentServiceXml, departmentServiceDerby);
        } catch (SaveObjectException | GetDataObjectException e) {
            logger.error("Ошибка при попытки импортирования данных", e);
        }
        try {
            List<Organization> organizationList = organizationServiceDerby.getAll();
            List<Person> personList = personServiceDerby.getAll();
            List<Department> departmentList = departmentServiceDerby.getAll();

            for (Department department : departmentList) {
                department.setManager(personList.get(new Random().nextInt(personList.size()-1)));
                department.setOrganization(organizationList.get(new Random().nextInt(2)));
                departmentServiceDerby.update(department);
            }
            for (Person person : personList) {
                person.setDepartment(departmentList.get(new Random().nextInt(departmentList.size()-1)));
                personServiceDerby.update(person);
            }
        } catch (GetDataObjectException | SaveObjectException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}