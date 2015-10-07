create table DELTA_STORAGE_CATEGORY_STATE (
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
    CATEGORY_ID varchar(36) not null,
    VOLUME double precision,
    SUMMARY_PRICE decimal(19, 2),
    DATE_TIME timestamp,
    --
    primary key (ID)
);
