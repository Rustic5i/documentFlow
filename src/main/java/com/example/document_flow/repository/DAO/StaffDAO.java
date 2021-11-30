package com.example.document_flow.repository.DAO;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.entity.staff.Staff;

import java.util.List;

public interface StaffDAO extends DAO<Staff> {

    List<Document> getDocumentByIdAuthor(int id);
}
