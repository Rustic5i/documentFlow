package com.example.document_flow.factory.generator;

import com.example.document_flow.entity.person.Person;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Класс — синглтон, с набором методов для предоставления рандомных данных
 * для наследников класса <code>Document</code>
 */
public class DataGenerator {

    private final Random random = new Random();

    private static DataGenerator dataGenerator;

    /**
     * Список Названия документа
     */
    private final List<String> name = Arrays.asList("Первый документ",
            "Второй документ",
            "Третий документ",
            "Четвертый документ");

    /**
     * Список Текста документа
     */
    private final List<String> text = Arrays.asList("Текстовый текст Документа",
            "Помыть кошку",
            "Сделай то, не знаю что",
            "Сходи туда, не знаю куда");

    /**
     * Список Адресатов
     */
    private final List<String> addressee = Arrays.asList("Астраханская область, город Щёлково, шоссе Косиора, 30",
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
    private final List<String> signOfControl = Arrays.asList("Признак контрольности 1",
            "Признак контрольности 2",
            "Признак контрольности 3");

    /**
     * Список Способов доставки
     */
    private final List<String> deliveryMethod = Arrays.asList("Почта России", "СДЭК", "Самовывоз");

    /**
     * Список Исходящих номеров
     */
    private final List<Long> outgoingNumber = Arrays.asList(1L, 2L, 3L, 4L);

    /**
     * Срок исполнения поручения
     */
    private final Date termOfExecution = new Date();

    /**
     * Список людей
     */
    public List<Person> personList = Arrays.asList(new Person("Кошелева Василиса Ивановна"),
            new Person("Ильина Вера Арсентьевна"),
            new Person("Новикова Есения Робертовна"),
            new Person("Розанова Полина Дмитриевна"),
            new Person("Симонова Есения Даниэльевна"),
            new Person("Орлов Глеб Михайлович"),
            new Person("Андреева Елизавета Павловна"));

    private DataGenerator() {
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
    public Person getAuthor() {
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
        return outgoingNumber.get(random.nextInt(outgoingNumber.size()));
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
        return (long) (random.nextDouble() * 100);
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
}
