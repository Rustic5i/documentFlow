package com.example.document_flow.entity.staff;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Элемент организационной структуры
 */
@XmlRootElement
public abstract class Staff {

    /**
     * Идентификатор
     */
    private long id;

    @XmlAttribute
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}