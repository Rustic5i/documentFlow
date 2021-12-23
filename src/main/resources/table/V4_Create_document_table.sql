create table DOCUMENT
(
    ID                  BIGINT not null
        constraint DOCUMENT_PK
            primary key,
    NAME                VARCHAR(50),
    TEXT                VARCHAR(255),
    REGISTRATION_NUMBER BIGINT,
    DATE_REGISTRATION   DATE,
    AUTHOR              BIGINT,
    FOREIGN KEY (AUTHOR) REFERENCES PERSON (ID)
        ON DELETE SET NULL
)