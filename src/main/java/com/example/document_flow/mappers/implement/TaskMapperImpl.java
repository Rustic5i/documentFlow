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
                .setId(rs.getLong(1))
                .setDateOfIssue(rs.getDate(2))
                .setTermOfExecution(rs.getDate(3))
                .setSignOfControl(rs.getString(5))
                .setName(rs.getString(8))
                .setText(rs.getString(9))
                .setRegistrationNumber(rs.getLong(10))
                .setDateRegistration(rs.getDate(11))
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
                        .setId(rs.getLong(21))
                        .setSurname(rs.getString(22))
                        .setName(rs.getString(23))
                        .setPatronymic(rs.getString(24))
                        .setPost(rs.getString(25))
                        .setDateOfBirth(rs.getDate(26))
                        .setPhoneNumber(rs.getInt(27))
                        .build())
                .setAuthor(new Person().newBuilder()
                        .setId(rs.getLong(29))
                        .setSurname(rs.getString(30))
                        .setName(rs.getString(31))
                        .setPatronymic(rs.getString(32))
                        .setPost(rs.getString(33))
                        .setDateOfBirth(rs.getDate(34))
                        .setPhoneNumber(rs.getInt(35))
                        .build())
                .build();
    }
}
