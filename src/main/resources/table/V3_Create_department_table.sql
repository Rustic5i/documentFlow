create table DEPARTMENT
(
    ID                   BIGINT not null
        constraint DEPARTMENT_PK
            primary key,
    FULL_NAME            VARCHAR(50),
    SHORT_NAME           VARCHAR(50),
    MANAGER_ID           BIGINT
        constraint MANAGER_ID
            references PERSON
            on delete set null,
    CONTACT_PHONE_NUMBER VARCHAR(50)
)