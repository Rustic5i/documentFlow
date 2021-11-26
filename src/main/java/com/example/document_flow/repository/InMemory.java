package com.example.document_flow.repository;

import java.util.WeakHashMap;

public class InMemory<T> {

    private WeakHashMap<String, T> objectWeakHashMap = new WeakHashMap<>();

    public void save(String path, T object) {
        objectWeakHashMap.put(path, object);
    }

    public T getObject(String path) {
        return objectWeakHashMap.get(path);
    }

    public boolean containsInMemory(String path) {
        return objectWeakHashMap.containsKey(path);
    }
}
