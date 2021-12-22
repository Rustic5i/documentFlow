package com.example.document_flow.factory.generator;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.factory.generator.entity.NameStaff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Класс — синглтон, с набором методов для предоставления рандомных данных
 * для наследников класса <code>Document</code>
 *
 * @author Баратов Руслан
 */
public class DataGenerator {

    private static final Random RANDOM = new Random();

    private static DataGenerator dataGenerator;

    /**
     * Лист телефонных номеров
     */
    private static final List<String> LIST_PHONE_NUMBER =  Arrays.asList("0(984)556-14-86",
            "3(7740)563-93-84",
            "559(4732)994-37-06",
            "0(844)008-03-64",
            "20(15)474-37-87",
            "83(974)294-28-46",
            "99(9284)327-70-64",
            "48(46)884-20-37");

    /**
     * Мапа наименований организаций
     */
    private static final Map<String, String> NAME_ORGANIZATION = initNamesOrganization();

    /**
     * Мапа наименований подразделений
     */
    private static final Map<String, String> NAMES_DEPARTMENT = initNamesDepartment();


    /**
     * Список Названия документа
     */
    private static final List<String> NAME = Arrays.asList("Первый документ",
            "Второй документ",
            "Третий документ",
            "Четвертый документ");

    /**
     * Список Текста документа
     */
    private static final List<String> TEXT = Arrays.asList("Текстовый текст Документа",
            "Помыть кошку",
            "Сделай то, не знаю что",
            "Сходи туда, не знаю куда");

    /**
     * Список Адресатов
     */
    private static final List<String> ADDRESSEE = Arrays.asList("Астраханская область, город Щёлково, шоссе Косиора, 30",
            "Курская область, город Павловский Посад, пр. Гагарина, 13",
            "Оренбургская область, город Орехово-Зуево, пл. Чехова, 18",
            "Кемеровская область, город Волоколамск, шоссе Гоголя, 14",
            "Амурская область, город Дорохово, ул. Косиора, 13",
            "Курская область, город Дмитров, наб. Сталина, 88",
            "Белгородская область, город Щёлково, пр. Чехова, 04",
            "Липецкая область, город Домодедово, пл. Гагарина, 42",
            "Тамбовская область, город Луховицы, пер. Ленина, 58");

    /**
     * Список Признак контрольности
     */
    private static final List<String> SIGN_OF_CONTROL = Arrays.asList("Признак контрольности 1",
            "Признак контрольности 2",
            "Признак контрольности 3");

    /**
     * Список Способов доставки
     */
    private static final List<String> DELIVERY_METHOD = Arrays.asList("Почта России", "СДЭК", "Самовывоз");

    /**
     * Срок исполнения поручения
     */
    private static final Date TERM_OF_EXECUTION = new Date();

    /**
     * Список людей
     */
    public final List<Person> PERSON_LIST = getPERSON_LIST();

    private DataGenerator() {
    }

    /**
     *
     * @return рандомный телефонный номер
     */
    public String getPhoneNumber(){
        return LIST_PHONE_NUMBER.get(RANDOM.nextInt(LIST_PHONE_NUMBER.size()));
    }

    /**
     *
     * @return проинициализируемую маппу с наименованиями Подразделений
     */
    private static Map<String, String> initNamesDepartment() {
        Map<String, String> namesDepartment = new HashMap<>();
        namesDepartment.put("AO \"Тандер\"", "Aкционерное общество \"Тандер\" ");
        namesDepartment.put("Востокгазпром (АО)", "Aкционерное общество  Востокгазпром");
        namesDepartment.put("ООО \"ЯНДЕКС.ТАКСИ\"", "ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ \"ЯНДЕКС.ТАКСИ\"");
        namesDepartment.put("ООО \"АВИТО ТЕХ\"", "ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ \"АВИТО ТЕХ\"");
        return namesDepartment;
    }

    /**
     *
     * @return проинициализируемую маппу с наименованиями Организаций
     */
    private static Map<String, String> initNamesOrganization() {
        Map<String, String> namesOrganization = new HashMap<>();
        namesOrganization.put("ПАО «Магнит»", "Публичное акционерное общество «Магнит»");
        namesOrganization.put("ООО \"Мэйл.ру\"", "Общество с ограниченной ответственностью \"Мэйл.ру\"");
        namesOrganization.put("АО \"Кфс\"", "Акционерное общество \"Кфс\"" );
        namesOrganization.put("ООО \"Самсунг\"", "Общество с ограниченной ответственностью \"Самсунг\"");

        return namesOrganization;
    }

    /**
     * @return обеьект хранящий в себя два значения, "Полное наименование" и "Краткое наименование"
     */
    public NameStaff getNamesDepartment() {
        String shortName = (String) NAMES_DEPARTMENT.keySet().toArray()[RANDOM.nextInt(NAME_ORGANIZATION.size())];
        String fullName = NAMES_DEPARTMENT.get(shortName);

        NameStaff names = new NameStaff();
        names.setShortName(shortName);
        names.setFullName(fullName);

        return names;
    }

    /**
     * @return обеьект хранящий в себя два значения, "Полное наименование" и "Краткое наименование"
     */
    public NameStaff getNamesOrganization() {
        String shortName = (String) NAME_ORGANIZATION.keySet().toArray()[RANDOM.nextInt(NAME_ORGANIZATION.size())];
        String fullName = NAME_ORGANIZATION.get(shortName);

        NameStaff names = new NameStaff();
        names.setShortName(shortName);
        names.setFullName(fullName);

        return names;
    }

    /**
     * Генерация случайных работников
     *
     * @return список сгенерируемых работников
     */
    private List<Person> getPERSON_LIST() {
        List<Person> personList = new ArrayList<>();

        String[] personName = new String[]{"Василиса", "Вера", "Есения", "Полина", "Глеб", "Ольга", "Елизавета"};
        String[] personSurname = new String[]{"Кошелева", "Ильина", "Новикова", "Розанова", "Симонова", "Орлов", "Андреева"};
        String[] personPatronymic = new String[]{"Ивановна", "Арсентьевна", "Робертовна", "Дмитриевна", "Даниэльевна", "Михайлович", "Павловна"};
        String[] personPost = new String[]{"Менеджер по работе с клиентами", "Юрист", "'Экономист", "Директор", "Главный бухгалтер"};
        int year = RANDOM.nextInt(2000 - 1990) + 1990;
        int month = RANDOM.nextInt(11);
        int day = RANDOM.nextInt(28);
        Calendar calendar = new GregorianCalendar(year, month, day);

        for (int i = 0; i < 10; i++) {
            Person person = new Person().newBuilder()
                    .setId(RANDOM.nextInt(1000))
                    .setName(personName[RANDOM.nextInt(personName.length)])
                    .setSurname(personSurname[RANDOM.nextInt(personSurname.length)])
                    .setPatronymic(personPatronymic[RANDOM.nextInt(personPatronymic.length)])
                    .setPost(personPost[RANDOM.nextInt(personPost.length)])
                    .setDateOfBirth(calendar.getTime())
                    .setPhoneNumber((int) (RANDOM.nextDouble() * 100000000))
                    .build();
            personList.add(person);
        }

        return personList;
    }

    /**
     * @return Рандомное имя
     */
    public String getName() {
        return NAME.get(RANDOM.nextInt(NAME.size()));
    }

    /**
     * @return Рандомный текст
     */
    public String getText() {
        return TEXT.get(RANDOM.nextInt(TEXT.size()));
    }

    /**
     * @return Ранодомный автор
     */
    public Person getPerson() {
        return PERSON_LIST.get(RANDOM.nextInt(PERSON_LIST.size()));
    }

    /**
     * @return Рандомный Oтправитель
     */
    public Person getSource() {
        return PERSON_LIST.get(RANDOM.nextInt(PERSON_LIST.size()));
    }

    /**
     * @return Рандомный адресат
     */
    public String getAddressee() {
        return ADDRESSEE.get(RANDOM.nextInt(ADDRESSEE.size()));
    }

    /**
     * @return Рандомный исходящий номер
     */
    public Long getOutgoingNumber() {
        return Long.valueOf(RANDOM.nextInt(100));
    }

    /**
     * @return Рандомный способ доставки
     */
    public String getDeliveryMethod() {
        return DELIVERY_METHOD.get(RANDOM.nextInt(DELIVERY_METHOD.size()));
    }

    /**
     * @return рандомный ответственный исполнитель
     */
    public Person getResponsibleExecutor() {
        return PERSON_LIST.get(RANDOM.nextInt(PERSON_LIST.size()));
    }

    /**
     * @return Рандомный признак контрольности
     */
    public String getSignOfControl() {
        return SIGN_OF_CONTROL.get(RANDOM.nextInt(SIGN_OF_CONTROL.size()));
    }

    /**
     * @return Рандомный Контролер поручения
     */
    public Person getOrderController() {
        return PERSON_LIST.get(RANDOM.nextInt(PERSON_LIST.size()));
    }

    /**
     * @return Рандомный регистрационный номер
     */
    public Long getRegistrationNumber() {
        return (long) (RANDOM.nextDouble() * 1000);
    }

    /**
     * @return Рандомный срок исполнения поручения
     */
    public Date getTermOfExecution() {
        final long DAY = 86400000L;
        TERM_OF_EXECUTION.setTime(getDateOfIssue().getTime() + DAY * (RANDOM.nextLong() * 10));
        return TERM_OF_EXECUTION;
    }

    /**
     * @return Рандомную дату регистрации документа
     */
    public Date getDateRegistration() {
        int year = RANDOM.nextInt(2022 - 2017) + 2017;
        int month = RANDOM.nextInt(11);
        int day = RANDOM.nextInt(28);
        Calendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTime();
    }

    /**
     * @return Рандомную Исходящую дату регистрации
     */
    public Date getOutgoingRegistrationDate() {
        int month = RANDOM.nextInt(12);
        int dayOfMonth = RANDOM.nextInt(28);
        Calendar calendar = new GregorianCalendar(2021, month, dayOfMonth);
        return calendar.getTime();
    }

    /**
     * @return Рандомную дату выдачу поручения
     */
    public Date getDateOfIssue() {
        int year = RANDOM.nextInt(2022 - 2017) + 2017;
        int month = RANDOM.nextInt(11);
        int day = RANDOM.nextInt(28);
        Calendar calendar = new GregorianCalendar(year, month, day);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * @return синголтон обьект
     */
    public static DataGenerator getInstance() {
        if (dataGenerator == null) {
            dataGenerator = new DataGenerator();
        }
        return dataGenerator;
    }

    public long getId() {
        return RANDOM.nextInt(100);
    }
}
