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
)