package com.example.document_flow.readWrite;

class XMLDeserializationTest {

//    private XMLDeserialization xmlDeserialization = new XMLDeserialization();
//
//    private Set<String> nameFills = new HashSet<>();
//
//    private List<Person> personList = new ArrayList<>();
//
//    @BeforeEach
//    void setUp() {
//        for (int i = 0; i < 3; i++) {
//            Person person = new Person();
//            person.setId(i);
//            person.setName("Андрей" + i);
//            person.setSurname("Сабиров" + i);
//            person.setPatronymic("Васильевич" + i);
//            person.setPost("Бугалтер" + i);
//            person.setDateOfBirth(new GregorianCalendar(1998, 12, 12).getTime());
//            person.setPhoneNumber(866555445 + i);
//            personList.add(person);
//        }
//
//        for (Person person : personList) {
//            try {
//                String nameFile = "person" + person.getId() + ".xml";
//                JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
//
//                Marshaller marshaller1 = jaxbContext.createMarshaller();
//                marshaller1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//                marshaller1.marshal(person, new File(nameFile));
//                nameFills.add(nameFile);
//            } catch (JAXBException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Test
//    void deserializationFromXml() throws JAXBException {
//        List<Person> actualPersonList = xmlDeserialization.deserializationFromXml(nameFills);
//
//        assertNotNull(actualPersonList);
//        assertTrue(personList.size()==actualPersonList.size());
//    }
//
//    @Test
//    void testDeserializationFromXml() throws JAXBException {
//        String nameFile = nameFills.stream().toList().get(0);
//        Person actualPerson = xmlDeserialization.deserializationFromXml(nameFile);
//
//        assertNotNull(actualPerson);
//        assertTrue(personList.stream().anyMatch(person -> person.equals(actualPerson)));
//    }
//
//    @AfterEach
//    void afterAll() {
//        for (String nameFills: nameFills) {
//            File file = new File(nameFills);
//            file.delete();
//        }
//    }
}