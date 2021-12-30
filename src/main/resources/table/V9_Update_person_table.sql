alter table PERSON
    add constraint PERSON_DEPARTMENT_ID_FK
        foreign key (ID_DEPARTMENT) references DEPARTMENT
            on delete set null