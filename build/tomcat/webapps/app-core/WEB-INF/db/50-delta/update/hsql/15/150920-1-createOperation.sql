create table DELTA_OPERATION (
    ID varchar(36) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    STORAGE_ID varchar(36) not null,
    MATERIAL_ID varchar(36) not null,
    VOLUME double precision not null,
    AVERAGE_PRICE decimal(19, 2) not null,
    SUMMARY_PRICE decimal(19, 2) not null,
    TYPE_ integer not null,
    DATE_TIME timestamp not null,
    --
    primary key (ID)
);
