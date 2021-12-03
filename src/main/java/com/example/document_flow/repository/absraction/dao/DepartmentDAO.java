package com.example.document_flow.repository.absraction.dao;

import com.example.document_flow.entity.staff.Department;

import java.util.List;

public interface DepartmentDAO {

    void saveDepartment(Department department);

    List<Department> getAllDepartment();

    void saveAllDepartment(List<Department> departmentList);

    Department getDepartmentById(long id);
}
