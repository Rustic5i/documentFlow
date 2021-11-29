package com.example.document_flow.entity.staff;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Элемент организационной структуры
 *
 * @author Баратов Руслан
 */
@XmlRootElement
public class Staff {

    /**
     * Идентификатор
     */
    private long id;

    public Staff() {
    }

    protected Staff(Builder<?> builder) {
        this.id = builder.id;
    }

    @XmlAttribute
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static class Builder<T extends Builder<T>> {

        /**
         * Идентификатор
         */
        private long id;

        public Builder() {
        }

        public T setId(long id) {
            this.id = id;
            return (T) this;
        }

        public Staff build() {
            return new Staff(this);
        }
    }
}