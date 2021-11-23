package com.example.document_flow.factory.staff;

import com.example.document_flow.entity.staff.Organization;

/**
 * Класс-фабрика для генерации Организация (Organization)
 *
 * @author Баратов Руслан
 */
public class OrganizationFactory extends AbstractStaffFactory<Organization> {

    /**
     * @return Тип создваемого обьекта
     */
    @Override
    public Class getTypeObject() {
        return Organization.class;
    }

    /**
     * Фабричный метод для создания объекта класса <code>Organization</code>
     *
     * @return новый инстанс класса <code>Organization</code>
     */
    @Override
    Organization createInstance() {
        return new Organization();
    }

    /**
     * Поля объекта заполняются случайными значениями
     *
     * @param staff обьект который нужно заполнить дополнительные поля
     */
    @Override
    void fillAdditionalFields(Organization staff) {
        staff.setFullName(getDataGenerator().getNamesOrganization().getFullName());
        staff.setShortName(getDataGenerator().getNamesOrganization().getShortName());
        staff.setManager(getDataGenerator().getPerson());
        staff.setContactPhoneNumber(getDataGenerator().gePhoneNumber());
    }
}
