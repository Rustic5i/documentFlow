create table      PERSON                   (
    ID            BIGINT not null
        constraint PERSON_PK
            primary key,
    SURNAME       VARCHAR(50),
    NAME          VARCHAR(50),
    PATRONYMIC    VARCHAR(50),
    POST          VARCHAR(100),
    DATA_OF_BIRTH DATE,
    PHONE_NUMBER  INTEGER
)

-- create table PERSON
-- (
--     ID            BIGINT not null
--         constraint PERSON_PK
--             primary key,
--     SURNAME       VARCHAR(50),
--     NAME          VARCHAR(50),
--     PATRONYMIC    VARCHAR(50),
--     POST          VARCHAR(100),
--     DATA_OF_BIRTH DATE,
--     PHONE_NUMBER  INTEGER,
--     ID_DEPARTMENT BIGINT
--         constraint PERSON_DEPARTMENT_ID_FK
--             references DEPARTMENT
--             on delete set null
-- )