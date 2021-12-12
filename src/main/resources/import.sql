create table PERSON
(
    ID            BIGINT not null
        constraint PERSON_PK
        primary key,
    SURNAME       VARCHAR(50),
    NAME          VARCHAR(50),
    PATRONYMIC    VARCHAR(50),
    POST          VARCHAR(100),
    DATA_OF_BIRTH DATE,
    PHONE_NUMBER  INTEGER
);
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
);
create table ORGANIZATION
(
    ID                   BIGINT not null
        constraint ORGANIZATION_PK
        primary key,
    FULL_NAME            VARCHAR(50),
    SHORT_NAME           VARCHAR(50),
    MANAGER_ID           BIGINT
        constraint MANAGER
            references PERSON
            on delete set null,
    CONTACT_PHONE_NUMBER VARCHAR(50)
);