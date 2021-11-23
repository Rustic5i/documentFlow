package com.example.document_flow.factory.generator;

import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.factory.generator.entity.Names;

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

    private final Random random = new Random();

    private static DataGenerator dataGenerator;

    /**
     * Лист телефонных номеров
     */
    private static List<String> listPhoneNumber =  Arrays.asList("0(984)556-14-86",
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
    private static final Map<String, String> namesOrganization = initNamesOrganization();

    /**
     * Мапа наименований подразделений
     */
    private static final Map<String, String> namesDepartment = initNamesDepartment();


    /**
     * Список Названия документа
     */
    private static final List<String> name = Arrays.asList("Первый документ",
            "Второй документ",
            "Третий документ",
            "Четвертый документ");

    /**
     * Список Текста документа
     */
    private static final List<String> text = Arrays.asList("Текстовый текст Документа",
            "Помыть кошку",
            "Сделай то, не знаю что",
            "Сходи туда, не знаю куда");

    /**
     * Список Адресатов
     */
    private static final List<String> addressee = Arrays.asList("Астраханская область, город Щёлково, шоссе Косиора, 30",
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
    private static final List<String> signOfControl = Arrays.asList("Признак контрольности 1",
            "Признак контрольности 2",
            "Признак контрольности 3");

    /**
     * Список Способов доставки
     */
    private static final List<String> deliveryMethod = Arrays.asList("Почта России", "СДЭК", "Самовывоз");

    /**
     * Срок исполнения поручения
     */
    private static final Date termOfExecution = new Date();

    /**
     * Список людей
     */
    public final List<Person> personList = getPersonList();

    private DataGenerator() {
    }

    /**
     *
     * @return рандомный телефонный номер
     */
    public String gePhoneNumber(){
        return listPhoneNumber.get(random.nextInt(listPhoneNumber.size()));
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
    public Names getNamesDepartment() {
        String shortName = (String) namesDepartment.keySet().toArray()[random.nextInt(namesOrganization.size())];
        String fullName = namesDepartment.get(shortName);

        Names names = new Names();
        names.setShortName(shortName);
        names.setFullName(fullName);

        return names;
    }

    /**
     * @return обеьект хранящий в себя два значения, "Полное наименование" и "Краткое наименование"
     */
    public Names getNamesOrganization() {
        String shortName = (String) namesOrganization.keySet().toArray()[random.nextInt(namesOrganization.size())];
        String fullName = namesOrganization.get(shortName);

        Names names = new Names();
        names.setShortName(shortName);
        names.setFullName(fullName);

        return names;
    }

    /**
     * Генерация случайных работников
     *
     * @return список сгенерируемых работников
     */
    private List<Person> getPersonList() {
        List<Person> personList = new ArrayList<>();

        String[] personName = new String[]{"Василиса", "Вера", "Есения", "Полина", "Глеб", "Ольга", "Елизавета"};
        String[] personSurname = new String[]{"Кошелева", "Ильина", "Новикова", "Розанова", "Симонова", "Орлов", "Андреева"};
        String[] personPatronymic = new String[]{"Ивановна", "Арсентьевна", "Робертовна", "Дмитриевна", "Даниэльевна", "Михайлович", "Павловна"};
        String[] personPost = new String[]{"Менеджер по работе с клиентами", "Юрист", "'Экономист", "Директор", "Главный бухгалтер"};
        int year = random.nextInt(2000 - 1990) + 1990;
        int month = random.nextInt(11);
        int day = random.nextInt(28);
        Calendar calendar = new GregorianCalendar(year, month, day);

        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setId(random.nextInt(100));
            person.setName(personName[random.nextInt(personName.length)]);
            person.setSurname(personSurname[random.nextInt(personSurname.length)]);
            person.setPatronymic(personPatronymic[random.nextInt(personPatronymic.length)]);
            person.setPost(personPost[random.nextInt(personPost.length)]);
            person.setDateOfBirth(calendar.getTime());
            person.setPhoneNumber((int) (random.nextDouble() * 100000000));

            personList.add(person);
        }

        return personList;
    }

    /**
     * @return Рандомное имя
     */
    public String getName() {
        return name.get(random.nextInt(name.size()));
    }

    /**
     * @return Рандомный текст
     */
    public String getText() {
        return text.get(random.nextInt(text.size()));
    }

    /**
     * @return Ранодомный автор
     */
    public Person getPerson() {
        return personList.get(random.nextInt(personList.size()));
    }

    /**
     * @return Рандомный Oтправитель
     */
    public Person getSource() {
        return personList.get(random.nextInt(personList.size()));
    }

    /**
     * @return Рандомный адресат
     */
    public String getAddressee() {
        return addressee.get(random.nextInt(addressee.size()));
    }

    /**
     * @return Рандомный исходящий номер
     */
    public Long getOutgoingNumber() {
        return Long.valueOf(random.nextInt(100));
    }

    /**
     * @return Рандомный способ доставки
     */
    public String getDeliveryMethod() {
        return deliveryMethod.get(random.nextInt(deliveryMethod.size()));
    }

    /**
     * @return рандомный ответственный исполнитель
     */
    public Person getResponsibleExecutor() {
        return personList.get(random.nextInt(personList.size()));
    }

    /**
     * @return Рандомный признак контрольности
     */
    public String getSignOfControl() {
        return signOfControl.get(random.nextInt(signOfControl.size()));
    }

    /**
     * @return Рандомный Контролер поручения
     */
    public Person getOrderController() {
        return personList.get(random.nextInt(personList.size()));
    }

    /**
     * @return Рандомный регистрационный номер
     */
    public Long getRegistrationNumber() {
        return (long) (random.nextDouble() * 1000);
    }

    /**
     * @return Рандомный срок исполнения поручения
     */
    public Date getTermOfExecution() {
        final long DAY = 86400000L;
        termOfExecution.setTime(getDateOfIssue().getTime() + DAY * (random.nextLong() * 10));
        return termOfExecution;
    }

    /**
     * @return Рандомную дату регистрации документа
     */
    public Date getDateRegistration() {
        int year = random.nextInt(2022 - 2017) + 2017;
        int month = random.nextInt(11);
        int day = random.nextInt(28);
        Calendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTime();
    }

    /**
     * @return Рандомную Исходящую дату регистрации
     */
    public Date getOutgoingRegistrationDate() {
        int month = random.nextInt(12);
        int dayOfMonth = random.nextInt(28);
        Calendar calendar = new GregorianCalendar(2021, month, dayOfMonth);
        return calendar.getTime();
    }

    /**
     * @return Рандомную дату выдачу поручения
     */
    public Date getDateOfIssue() {
        int year = random.nextInt(2022 - 2017) + 2017;
        int month = random.nextInt(11);
        int day = random.nextInt(28);
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
        return random.nextInt(100);
    }
}
