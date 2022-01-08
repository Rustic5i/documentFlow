package com.example.document_flow.factory.staff;

import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.factory.Factory;
import com.example.document_flow.factory.generator.DataGenerator;

/**
 * Класс-фабрика для генерации объектов класса {@link Organization}
 *
 * @author Баратов Руслан
 */
public class OrganizationFactory implements Factory<Organization> {

    private static OrganizationFactory organizationFactory;

    private final DataGenerator dataGenerator = DataGenerator.getInstance();

    private OrganizationFactory() {
    }

    /**
     * @return синголтон обьект
     */
    public static OrganizationFactory getInstance() {
        if (organizationFactory == null) {
            organizationFactory = new OrganizationFactory();
        }
        return organizationFactory;
    }

    /**
     * Реализация фабричного метода
     *
     * @return возвращает новый инстанс класса {@link Organization} с заполненными поля
     */
    @Override
    public Organization create() {
        return new Organization().newBuilder()
                .setId(dataGenerator.getId())
                .setContactPhoneNumber(dataGenerator.getPhoneNumber())
                .setShortName(dataGenerator.getNamesOrganization().getShortName())
                .setFullName(dataGenerator.getNamesOrganization().getFullName())
                .build();
    }

    /**
     * @return Тип создаваемого объекта
     */
    @Override
    public Class getTypeObject() {
        return Organization.class;
    }
}
