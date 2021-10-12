package com.example.document_flow.factory.generate;

import com.example.document_flow.model.Document;
import com.example.document_flow.model.person.Person;
import com.example.document_flow.model.person.Persons;
import com.example.document_flow.myException.DocumentExistsException;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.*;

@Getter
public abstract class GenerateDocument {

    private static List<String> name;

    private static List<String> text;

    private static Set<Long> registrationNumber;//регистрационный номер документа

    private static SimpleDateFormat dateRegistration = new SimpleDateFormat("EEEE, d MMMM yyyy"); //дата регистрации документа;

    private static List <Person> author;

    private static Random random = new Random();

    static {
        author = Persons.names;

        name = new ArrayList<>();
        name.add("Первый документ");
        name.add("Второй документ");
        name.add("Третий документ");
        name.add("Четвертый документ");

        text = new ArrayList<>();
        text.add("Текстовый текст Документа");
        text.add("Помыть кошку");
        text.add("Сделай то, не знаю что");
        text.add("Сходи туда, не знаю куда");

        registrationNumber = new HashSet<>();
    }

    private static Long getRandomRegNumber() throws DocumentExistsException {
        Long regNumber = null;
        regNumber = random.nextLong(10);
        if (registrationNumber.contains(regNumber)) {
            throw new DocumentExistsException("Document с таким регистрационным номер уже есть ");
        } else {
            registrationNumber.add(regNumber);
            return regNumber;
        }
    }

    public static Document getRandomInstance(Document document) throws DocumentExistsException {
        document.setName(name.get(random.nextInt(name.size())));
        document.setText(text.get(random.nextInt(text.size())));
        document.setAuthor(author.get(random.nextInt(author.size())));
        document.setDateRegistration(dateRegistration);
        document.setRegistrationNumber(getRandomRegNumber());
        return document;
    }
}
