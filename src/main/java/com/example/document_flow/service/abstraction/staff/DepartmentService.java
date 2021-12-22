package com.example.document_flow.service.abstraction.staff;

import com.example.document_flow.entity.staff.Department;
import com.example.document_flow.exception.DeleteObjectException;
import com.example.document_flow.exception.SaveObjectException;
import com.example.document_flow.service.abstraction.Service;

import java.util.Optional;

/**
 * Интерфейс сервис для управления Department
 *
 * @author Баратов Руслан
 */
public interface DepartmentService extends Service<Department> {
}
