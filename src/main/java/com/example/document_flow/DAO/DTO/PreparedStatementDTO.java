package com.example.document_flow.DAO.DTO;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.entity.staff.Organization;
import com.example.document_flow.entity.staff.Person;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Класс DTO, транспортирует данные из Entity-объект в <code>PreparedStatement</code>
 *
 * @author Баратов Руслан
 */
public class PreparedStatementDTO {

    /**
     * Транспортирует данные объекта класса <code>Person</code> в <code>PreparedStatement</code>.
     *
     * @param person            от куда переносить данные.
     * @param preparedStatement проинцелезируемый объект (куда переносить).
     * @return готовый объект <code>PreparedStatement</code>
     * @throws SQLException если parameterIndex не соответствует маркеру параметра в инструкции SQL;
     *                      если возникла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении.
     */
    public static PreparedStatement transfer(Person person, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, person.getSurname());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getPatronymic());
        preparedStatement.setString(4, person.getPost());
        preparedStatement.setDate(5, new Date(person.getDateOfBirth().getTime()));
        preparedStatement.setInt(6, person.getPhoneNumber());
        preparedStatement.setLong(7, person.getId());
        return preparedStatement;
    }

    /**
     * Транспортирует данные объекта класса <code>Department</code> в <code>PreparedStatement</code>.
     *
     * @param department        от куда переносить данные.
     * @param preparedStatement проинцелезируемый объект (куда переносить данные).
     * @return готовый объект <code>PreparedStatement</code>.
     * @throws SQLException если parameterIndex не соответствует маркеру параметра в инструкции SQL;
     *                      если возникла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении.
     */
    public static PreparedStatement transfer(Department department, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, department.getFullName());
        preparedStatement.setString(2, department.getShortName());
        preparedStatement.setLong(3, department.getManager().getId());
        preparedStatement.setString(4, department.getContactPhoneNumber());
        preparedStatement.setLong(5, department.getId());
        return preparedStatement;
    }

    /**
     * Транспортирует данные объекта класса <code>Organization</code> в <code>PreparedStatement</code>
     *
     * @param organization      от куда переносить данные.
     * @param preparedStatement проинцелезируемый объект (куда переносить данные)
     * @return готовый объект <code>PreparedStatement</code>
     * @throws SQLException если parameterIndex не соответствует маркеру параметра в инструкции SQL;
     *                      если возникла ошибка доступа к базе данных или этот метод вызывается при закрытом соединении
     */
    public static PreparedStatement transfer(Organization organization, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, organization.getFullName());
        preparedStatement.setString(2, organization.getShortName());
        preparedStatement.setLong(3, organization.getManager().getId());
        preparedStatement.setString(4, organization.getContactPhoneNumber());
        preparedStatement.setLong(5, organization.getId());
        return preparedStatement;
    }

}
