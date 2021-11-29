package com.example.document_flow.validation;

import com.example.document_flow.exception.InvalidParametersException;

/**
 * Класс занимается валидацией параметров
 * @author Баратов Руслан
 */
public class ParametersValidation {

    /**
     * Проверяет, представляет ли строка целое число
     * @param args строка, содержащая представление int, подлежащее анализу
     * @return целочисленное значение, представленное аргументом в десятичной системе счисления
     * @throws InvalidParametersException  если строка не является целым числом
     */
    public static int isNumber(String args) throws InvalidParametersException {
        int count = 0;
        if (args != null) {
            try {
                count = Integer.parseInt(args);
            } catch (NumberFormatException e) {
               throw new InvalidParametersException("Exception: строка не является числом");
            }
            return count;
        }
        throw new InvalidParametersException("Параметры программы не заданы");
    }

}
