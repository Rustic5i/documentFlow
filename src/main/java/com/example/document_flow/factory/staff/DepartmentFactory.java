package com.example.document_flow.factory.staff;

import com.example.document_flow.entity.staff.Department;

/**
 * Класс-фабрика для генерации Подразделений (Department)
 *
 * @author Баратов Руслан
 */
public class DepartmentFactory extends AbstractStaffFactory<Department> {

    /**
     * Фабричный метод для создания объекта класса <code>Department</code>
     *
     * @return новый инстанс класса <code>Department</code>
     */
    @Override
    Department createInstance() {
        return new Department();
    }

    /**
     * Поля объекта заполняются случайными значениями
     *
     * @param staff обьект который нужно заполнить дополнительные поля
     */
    @Override
    void fillAdditionalFields(Department staff) {
        staff.setFullName(getDataGenerator().getNamesDepartment().getFullName());
        staff.setShortName(getDataGenerator().getNamesDepartment().getShortName());
        staff.setContactPhoneNumber(getDataGenerator().gePhoneNumber());
        staff.setManager(getDataGenerator().getPerson());
    }

    /**
     * @return Тип создваемого обьекта
     */
    @Override
    public Class getTypeObject() {
        return Department.class;
    }
}
