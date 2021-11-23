package com.example.document_flow.factory.staff;

import com.example.document_flow.entity.staff.Staff;
import com.example.document_flow.factory.Factory;
import com.example.document_flow.factory.generator.DataGenerator;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStaffFactory<T extends Staff> implements Factory<Staff> {

    private final DataGenerator dataGenerator = DataGenerator.getInstance();

    /**
     * Реализация фабричного метода
     *
     * @return возращает новый инстанс Staff с заполнеными поля
     */
    @Override
    public T create() {
        T staff = createInstance();
        staff.setId(dataGenerator.getId());
        fillAdditionalFields(staff);
        return staff;
    }

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
     * @param staff
     */
    abstract void fillAdditionalFields(T staff);

    public DataGenerator getDataGenerator() {
        return dataGenerator;
    }
}
