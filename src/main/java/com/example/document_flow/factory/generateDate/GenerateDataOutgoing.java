package com.example.document_flow.factory.generateDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс для хранения и предоставления рандомных данных для документа {@link com.example.document_flow.model.Outgoing}
 */
public class GenerateDataOutgoing {

    private List<String> addressee;       //адресат

    private List<String> deliveryMethod; //способ доставки

    private Random random = new Random();

     {
        addressee = new ArrayList<>();
        addressee.add("Амурская область, город Дорохово, ул. Косиора, 13");
        addressee.add("Курская область, город Дмитров, наб. Сталина, 88");
        addressee.add("Белгородская область, город Щёлково, пр. Чехова, 04");
        addressee.add("Липецкая область, город Домодедово, пл. Гагарина, 42");
        addressee.add("Тамбовская область, город Луховицы, пер. Ленина, 58");

        deliveryMethod = new ArrayList<>();
        deliveryMethod.add("Почта России");
        deliveryMethod.add("СДЭК");
        deliveryMethod.add("Самовывоз");
    }

    public String getAddressee() {
        return addressee.get(random.nextInt(addressee.size()));
    }

    public String getDeliveryMethod() {
        return deliveryMethod.get(random.nextInt(deliveryMethod.size()));
    }
}
