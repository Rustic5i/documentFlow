package com.example.document_flow.factory.staff;

import com.example.document_flow.entity.staff.Staff;
import com.example.document_flow.factory.Factory;
import com.example.document_flow.factory.generator.DataGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактаня фабрика для создания различных организационных структур
 *
 * @param <T> типо организационных структур
 * @author Баратов Руслан
 */
public abstract class AbstractStaffFactory<T extends Staff> implements Factory<Staff> {

    private final DataGenerator dataGenerator = DataGenerator.getInstance();

    /**
     * Реализация фабричного метода
     *
     * @return возращает новый инстанс с заполнеными поля
     */
    @Override
    public T create() {
        T staff = createInstance();
        staff.setId(dataGenerator.getId());
        fillAdditionalFields(staff);
        return staff;
    }

    /**
     * Реализация фабричного метода
     * @param count количество создоваемых обьектов
     * @return лист созданных обьектов
     */
    @Override
    public List<Staff> creatListObject(int count) {
        List<Staff> staffList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            staffList.add(create());
        }
        return staffList;
    }

    /**
     * Фабричный метод по созданию инстансов “Организационных структур”
     *
     * @return документ
     */
    abstract T createInstance();

    /**
     * Метод по заполения дополнительных полей “Организационных структур”
     *
     * @param staff обьект который нужно заполнить дополнительные поля
     */
    abstract void fillAdditionalFields(T staff);

    public DataGenerator getDataGenerator() {
        return dataGenerator;
    }
}
