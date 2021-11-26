package com.example.document_flow.util;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.entity.document.Incoming;
import com.example.document_flow.entity.document.Outgoing;
import com.example.document_flow.entity.document.Task;
import com.example.document_flow.entity.staff.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//public class DocumentGroupServiceTest {
//
//    private DocumentGroupService grouper = new DocumentGroupService();
//
//    private ArrayList<Document> documentList = new ArrayList<>();
//
//    private Map<Person, List<Document>> expectedMap = new HashMap<>();
//
//    @Test
//    void groupByAuthor() {
//        Map<Person, List<Document>> actualMap = grouper.groupByAuthor(documentList);
//
//        assertNotNull(actualMap);
//        assertEquals(expectedMap, actualMap);
//    }
//
//    @BeforeEach
//    void setUp() {
//        Person andrey = new Person();
//        andrey.setId(1);
//        andrey.setName("Василий");
//        andrey.setSurname("Воробьев");
//        andrey.setPatronymic("Андреевич");
//        andrey.setPost("Юрист");
//        andrey.setDateOfBirth(new GregorianCalendar(1995,02,1).getTime());
//        andrey.setPhoneNumber(899944444);
//
//        Person natasha = new Person();
//        natasha.setId(2);
//        natasha.setName("Андрей");
//        natasha.setSurname("Сабиров");
//        natasha.setPatronymic("Васильевич");
//        natasha.setPost("Бугалтер");
//        natasha.setDateOfBirth(new GregorianCalendar(1998,12,12).getTime());
//        natasha.setPhoneNumber(866555445);
//
//        Person ruslan = new Person();
//        ruslan.setId(3);
//        ruslan.setName("Ольга");
//        ruslan.setSurname("Сергеевна");
//        ruslan.setPatronymic("Петовна");
//        ruslan.setPost("Бугалтер");
//        ruslan.setDateOfBirth(new GregorianCalendar(1997,5,14).getTime());
//        ruslan.setPhoneNumber(89855544);
//
//        for (Person person : Arrays.asList(andrey, natasha, ruslan)) {
//            Long count = 1L;
//
//            Incoming incoming = new Incoming();
//            incoming.setAuthor(person);
//            incoming.setRegistrationNumber((long) 1 + count);
//            incoming.setDateRegistration(new GregorianCalendar(2018, 5, 11).getTime());
//            incoming.setName("Входящий документ" + count);
//            documentList.add(incoming);
//
//            Outgoing outgoing = new Outgoing();
//            outgoing.setAuthor(person);
//            outgoing.setRegistrationNumber((long) 2 + count);
//            outgoing.setDateRegistration(new GregorianCalendar(2019, 6, 12).getTime());
//            outgoing.setName("Исходящий документ" + count);
//            documentList.add(outgoing);
//
//            Task task = new Task();
//            task.setAuthor(person);
//            task.setRegistrationNumber((long) 3 + count);
//            task.setDateRegistration(new GregorianCalendar(2020, 7, 13).getTime());
//            task.setName("Поручение" + count);
//            documentList.add(task);
//            count++;
//
//            expectedMap.put(person, Arrays.asList(incoming, outgoing, task));
//        }
//    }
//
//
//}