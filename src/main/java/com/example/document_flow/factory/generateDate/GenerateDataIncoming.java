package com.example.document_flow.factory.generateDate;

import com.example.document_flow.model.person.Person;
import java.util.*;

/**
 * Класс для хранения и предоставления рандомных данных для документа {@link com.example.document_flow.model.Incoming}
 */
public class GenerateDataIncoming extends GenerateDataDocument {

    private List<Person> source; // отправитель

    private List<String> addressee; //addressee

    private List<Long> outgoingNumber; // исходящий номер

    private Random random = new Random();

    private static GenerateDataIncoming generateDataIncoming;

    private GenerateDataIncoming() {
    }

    {
        source = GeneratePersons.names;

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

    public Date generateDate() {
        int month = random.nextInt(12);
        int dayOfMonth = random.nextInt(28);
        Calendar calendar = new GregorianCalendar(2021, month, dayOfMonth);
        Date date = calendar.getTime();
        return date;
    }

    public Person getSource() {
        return source.get(random.nextInt(source.size()));
    }

    public String getAddressee() {
        return addressee.get(random.nextInt(addressee.size()));
    }

    public Long getOutgoingNumber() {
        return outgoingNumber.get(random.nextInt(outgoingNumber.size()));
    }

    public static GenerateDataIncoming getInstance() {
        if (generateDataIncoming == null) {
            generateDataIncoming = new GenerateDataIncoming();
        }
        return generateDataIncoming;
    }
}
