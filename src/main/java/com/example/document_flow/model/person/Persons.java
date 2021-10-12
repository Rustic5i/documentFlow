package com.example.document_flow.model.person;

import java.util.ArrayList;
import java.util.List;


public abstract class Persons {
    public static List<Person> names;

    static {
        names = new ArrayList<>();
        names.add(new Person("Кошелева Василиса Ивановна"));
        names.add(new Person("Ильина Вера Арсентьевна"));
        names.add(new Person("Новикова Есения Робертовна"));
        names.add(new Person("Розанова Полина Дмитриевна"));
        names.add(new Person("Симонова Есения Даниэльевна"));
        names.add(new Person("Орлов Глеб Михайлович"));
        names.add(new Person("Андреева Елизавета Павловна"));
    }

}
