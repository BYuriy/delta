create table DELTA_STORAGE_CATEGORY_STATE (
    ID uuid,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CATEGORY_ID uuid not null,
    VOLUME double precision,
    SUMMARY_PRICE decimal(19, 2),
    DATE_TIME timestamp,
    STORAGE_ID uuid not null,
    --
    primary key (ID)
);
