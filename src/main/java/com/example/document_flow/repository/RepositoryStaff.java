package com.example.document_flow.repository;

import com.example.document_flow.entity.staff.Staff;

import java.util.ArrayList;
import java.util.List;

public class RepositoryStaff {

    private List<Staff> staffList = new ArrayList<>();

    public void saveAll(List<Staff> staffList){
        this.staffList.addAll(staffList);
    }

    public void save (Staff staff){
        staffList.add(staff);
    }

    public List<Staff> getAllStaff(){
        return staffList;
    }
}
