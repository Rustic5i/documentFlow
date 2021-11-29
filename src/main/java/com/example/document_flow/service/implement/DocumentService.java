package com.example.document_flow.service.implement;

import com.example.document_flow.entity.document.Document;
import com.example.document_flow.repository.document.DocumentRepository;
import com.example.document_flow.service.abstraction.Service;

import java.util.List;

public class DocumentService implements Service<Document> {

    private DocumentRepository repository = DocumentRepository.getInstance();

    @Override
    public void save(Document object) {
        repository.save(object);
    }

    @Override
    public void saveAll(List<Document> objects) {
        repository.saveAll(objects);
    }

    @Override
    public List<Document> getAll() {
        return repository.getAll();
    }

}
