package com.example.document_flow.factory.generate;

import com.example.document_flow.model.Outgoing;
import com.example.document_flow.myException.DocumentExistsException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public abstract class GenerateOutgoing extends GenerateDocument {

    private static List<String> addressee;       //адресат

    private static List<String> deliveryMethod; //способ доставки

    private static Random random = new Random();

    static {
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

    public static Outgoing getRandomInstance() {
        Outgoing outgoing = new Outgoing();
        try {
            outgoing = (Outgoing) getRandomInstance(outgoing);
            outgoing.setAddressee(addressee.get(random.nextInt(addressee.size())));
            outgoing.setDeliveryMethod(deliveryMethod.get(random.nextInt(deliveryMethod.size())));
            return outgoing;
        } catch (DocumentExistsException e) {
            e.printStackTrace();
        }
        return null;
    }
}
