create table DEPARTMENT
(
    ID                   INTEGER not null
        constraint DEPARTMENT_PK
            primary key,
    FULL_NAME            VARCHAR(25),
    SHORT_NAME           VARCHAR(25),
    MANAGER_ID           INTEGER
        constraint MANAGER_ID
            references PERSON,
    CONTACT_PHONE_NUMBER VARCHAR(25)
);
create table ORGANIZATION
(
    ID                   INTEGER not null
        constraint ORGANIZATION_PK
            primary key,
    FULL_NAME            VARCHAR(25),
    SHORT_NAME           VARCHAR(25),
    MANAGER_ID           INTEGER
        constraint MANAGER
            references PERSON,
    CONTACT_PHONE_NUMBER VARCHAR(25)
);
create table PERSON
(
    ID            INTEGER not null
        constraint PERSON_PK
            primary key,
    SURNAME       VARCHAR(25),
    NAME          VARCHAR(25),
    PATRONYMIC    VARCHAR(25),
    POST          VARCHAR(100),
    DATA_OF_BIRTH DATE,
    PHONE_NUMBER  INTEGER
);
