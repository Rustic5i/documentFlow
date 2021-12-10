package com.example.document_flow;

import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.service.implement.staff.xml.PersonServiceXmlImpl;
import com.example.document_flow.web.controller.DocumentRequestHandler;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws LifecycleException, ServletException, DeleteObjectException, SaveObjectException {
//        DocumentRequestHandler requestHandler = new DocumentRequestHandler();
//        requestHandler.setRequest(args);

        PersonServiceXmlImpl personServiceXml = PersonServiceXmlImpl.getInstance();
        personServiceXml.getAll();
        String contextPath = "/";
        String webappDir = new File("").getAbsolutePath();

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(8080);

        tomcat.addWebapp(contextPath, webappDir);
        String RESOURCE_PATH = Thread.currentThread().getContextClassLoader().getResource("config/config.properties").getPath();
        System.out.println(RESOURCE_PATH);
        tomcat.start();
        tomcat.getServer().await();
    }

}
