package com.example.document_flow.factory.staff;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.factory.Factory;
import com.example.document_flow.factory.generator.DataGenerator;


/**
 * Класс-фабрика для генерации объектов класса {@link Department}
 *
 * @author Баратов Руслан
 */
public class DepartmentFactory implements Factory<Department> {

    private static DepartmentFactory departmentFactory;

    private final DataGenerator dataGenerator = DataGenerator.getInstance();

    private DepartmentFactory() {
    }

    /**
     * @return синголтон обьект
     */
    public static DepartmentFactory getInstance() {
        if (departmentFactory == null) {
            departmentFactory = new DepartmentFactory();
        }
        return departmentFactory;
    }

    /**
     * Реализация фабричного метода
     *
     * @return возвращает новый инстанс класса {@link Department} с заполненными поля
     */
    @Override
    public Department create() {
        return new Department().newBuilder()
                .setId(dataGenerator.getId())
                .setContactPhoneNumber(dataGenerator.getPhoneNumber())
                .setFullName(dataGenerator.getNamesDepartment().getFullName())
                .setShortName(dataGenerator.getNamesDepartment().getShortName())
                .build();
    }

    /**
     * @return Тип создаваемого объекта
     */
    @Override
    public Class getTypeObject() {
        return Department.class;
    }
}
