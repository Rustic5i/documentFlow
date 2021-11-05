package com.example.document_flow.factory.generateDate;

import com.example.document_flow.model.person.Person;
import com.example.document_flow.myException.DocumentExistsException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GenerateDataDocument {

    private List<String> name;

    private List<String> text;

    private Set<Long> registrationNumber;//регистрационный номер документа

    private Date dateRegistration; //дата регистрации документа;

    private List<Person> author;

    private Random random = new Random();

    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        dateRegistration = calendar.getTime();

        author = GeneratePersons.names;

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

    public Long getRandomRegNumber() throws DocumentExistsException {
        Long regNumber = random.nextLong(100);
        if (registrationNumber.contains(regNumber)) {
            throw new DocumentExistsException("Document с регистрационным номер "+regNumber+" уже существует ");
        } else {
            registrationNumber.add(regNumber);
            return regNumber;
        }
    }

    public String getName() {
        return name.get(random.nextInt(name.size()));
    }

    public String getText() {
        return text.get(random.nextInt(text.size()));
    }

    public Person getAuthor() {
        return author.get(random.nextInt(author.size()));
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }
}
