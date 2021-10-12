package com.example.document_flow.factory.generate;

import com.example.document_flow.model.Incoming;
import com.example.document_flow.model.person.Person;
import com.example.document_flow.model.person.Persons;
import com.example.document_flow.myException.DocumentExistsException;

import java.util.*;

public class GenerateIncoming extends GenerateDocument{

    private static List <Person> source; // отправитель

    private static List <String> addressee; //addressee

    private static List <Long> outgoingNumber; // исходящий номер


    private static Random random = new Random();

    static {
        source = Persons.names;

        addressee = new ArrayList<>();
        addressee.add("Астраханская область, город Щёлково, шоссе Косиора, 30");
        addressee.add("Курская область, город Павловский Посад, пр. Гагарина, 13");
        addressee.add("Оренбургская область, город Орехово-Зуево, пл. Чехова, 18");
        addressee.add("Кемеровская область, город Волоколамск, шоссе Гоголя, 14");

        outgoingNumber = new ArrayList<>();
        outgoingNumber.add(1L);
        outgoingNumber.add(2L);
        outgoingNumber.add(3L);
        outgoingNumber.add(4L);

    }

    private static Date geterateDate(){
        int month = random.nextInt(12);
        int dayOfMonth = random.nextInt(28);
        Calendar calendar = new GregorianCalendar(2021,month,dayOfMonth);
        Date date = calendar.getTime();
        return date;
    }

    public static Incoming getRandomInstance() {
        Incoming incoming = new Incoming();
        try {
            incoming = (Incoming) getRandomInstance(incoming);
            incoming.setSource(source.get(random.nextInt(source.size())));
            incoming.setAddressee(addressee.get(random.nextInt(addressee.size())));
            incoming.setOutgoingNumber(outgoingNumber.get(random.nextInt(outgoingNumber.size())));
            incoming.setOutgoingRegistrationDate(geterateDate());
            return incoming;
        }catch (DocumentExistsException e){
            e.printStackTrace();
        }
        return null;
    }
}
