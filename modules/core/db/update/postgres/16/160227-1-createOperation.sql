create table DELTA_OPERATION (
    ID uuid,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    STORAGE_ID uuid not null,
    CATEGORY_ID uuid not null,
    VOLUME double precision not null,
    SUMMARY_PRICE decimal(19, 2),
    TYPE_ integer not null,
    DATE_TIME date not null,
    DESCRIPTION varchar(150),
    STORAGE_DESTINATION_ID uuid,
    --
    primary key (ID)
);
