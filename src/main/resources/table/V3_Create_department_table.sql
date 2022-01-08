create table DEPARTMENT
(
    ID                   BIGINT not null
        constraint DEPARTMENT_PK
            primary key,
    FULL_NAME            VARCHAR(250),
    SHORT_NAME           VARCHAR(250),
    MANAGER_ID           BIGINT
        constraint MANAGER_ID
            references PERSON
            on delete set null,
    CONTACT_PHONE_NUMBER VARCHAR(250),
    ID_ORGANIZATION      BIGINT
        constraint ORGANIZATION__FK
            references ORGANIZATION
            on delete set null
)