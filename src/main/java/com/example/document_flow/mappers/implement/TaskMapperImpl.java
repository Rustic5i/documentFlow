package com.example.document_flow.mappers.implement;

import com.example.document_flow.entity.document.Task;
import com.example.document_flow.entity.staff.Person;
import com.example.document_flow.mappers.absraction.TaskMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapperImpl implements TaskMapper {

    private static TaskMapperImpl taskMapper;

    private TaskMapperImpl() {
    }

    /**
     * @return синголтон обьект
     */
    public static TaskMapperImpl getInstance() {
        if (taskMapper == null) {
            taskMapper = new TaskMapperImpl();
        }
        return taskMapper;
    }

    /**
     * Преобразует данные {@link ResultSet} в Entity-объект класса {@link Task}
     *
     * @param rs от куда переносить данные
     * @return объекты с заполненными полями
     * @throws SQLException если метка столбца недоступна;
     *                      если произошла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении
     */
    @Override
    public Task convertFrom(ResultSet rs) throws SQLException {
        return new Task().newBuilder()
                .setDateOfIssue(rs.getDate(1))
                .setTermOfExecution(rs.getDate(2))
                .setSignOfControl(rs.getString(4))
                .setName(rs.getString(5))
                .setText(rs.getString(6))
                .setRegistrationNumber(rs.getLong(7))
                .setDateRegistration(rs.getDate(8))
                .setResponsibleExecutor(new Person().newBuilder()
                        .setId(rs.getLong(13))
                        .setSurname(rs.getString(14))
                        .setName(rs.getString(15))
                        .setPatronymic(rs.getString(16))
                        .setPost(rs.getString(17))
                        .setDateOfBirth(rs.getDate(18))
                        .setPhoneNumber(rs.getInt(19))
                        .build())
                .setOrderController(new Person().newBuilder()
                        .setId(rs.getLong(20))
                        .setSurname(rs.getString(21))
                        .setName(rs.getString(22))
                        .setPatronymic(rs.getString(23))
                        .setPost(rs.getString(24))
                        .setDateOfBirth(rs.getDate(25))
                        .setPhoneNumber(rs.getInt(26))
                        .build())
                .setAuthor(new Person().newBuilder()
                        .setId(rs.getLong(27))
                        .setSurname(rs.getString(28))
                        .setName(rs.getString(29))
                        .setPatronymic(rs.getString(30))
                        .setPost(rs.getString(31))
                        .setDateOfBirth(rs.getDate(32))
                        .setPhoneNumber(rs.getInt(33))
                        .build())
                .build();
    }
}
