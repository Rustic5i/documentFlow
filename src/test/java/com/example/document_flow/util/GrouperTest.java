package com.example.document_flow.util;

import com.example.document_flow.entity.Document;
import com.example.document_flow.entity.Incoming;
import com.example.document_flow.entity.Outgoing;
import com.example.document_flow.entity.Task;
import com.example.document_flow.entity.person.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GrouperTest {

    private Grouper grouper = new Grouper();

    private ArrayList<Document> documentList = new ArrayList<>();

    private Map<Person, List<Document>> expectedMap = new HashMap<>();

    @Test
    void groupByAuthor() {
        Map<Person, List<Document>> actualMap = grouper.groupByAuthor(documentList);

        assertNotNull(actualMap);
        assertEquals(expectedMap, actualMap);
    }

    @BeforeEach
    void setUp() {
        Person andrey = new Person("Андрей");
        Person natasha = new Person("Ольга");
        Person ruslan = new Person("Руслан");

        for (Person person : Arrays.asList(andrey, natasha, ruslan)) {
            Long count = 1L;

            Incoming incoming = new Incoming();
            incoming.setAuthor(person);
            incoming.setRegistrationNumber((long) 1 + count);
            incoming.setDateRegistration(new GregorianCalendar(2018, 5, 11).getTime());
            incoming.setName("Входящий документ" + count);
            documentList.add(incoming);

            Outgoing outgoing = new Outgoing();
            outgoing.setAuthor(person);
            outgoing.setRegistrationNumber((long) 2 + count);
            outgoing.setDateRegistration(new GregorianCalendar(2019, 6, 12).getTime());
            outgoing.setName("Исходящий документ" + count);
            documentList.add(outgoing);

            Task task = new Task();
            task.setAuthor(person);
            task.setRegistrationNumber((long) 3 + count);
            task.setDateRegistration(new GregorianCalendar(2020, 7, 13).getTime());
            task.setName("Поручение" + count);
            documentList.add(task);
            count++;

            expectedMap.put(person, Arrays.asList(incoming, outgoing, task));
        }
    }

}