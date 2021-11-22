package com.example.document_flow.entity.document;

/**
 * Перечисление типов документов.
 * @author Баратов Руслан.
 */
public enum DocumentType {

    /**
     * Представляет тип класса <code>Incoming</code>
     */
    INCOMING{
        @Override
        public Class getType() {
            return Incoming.class;
        }
    },
    /**
     * Представляет тип класса <code>Outgoing</code>
     */
    OUTGOING{
        @Override
        public Class getType() {
            return Outgoing.class;
        }
    },
    /**
     * Представляет тип класса <code>Task</code>
     */
    TASK{
        @Override
        public Class getType() {
            return Task.class;
        }
    };

    public abstract Class getType();
}
