create table DELTA_PRICE (
    ID uuid,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PRICE decimal(19, 2),
    START_DATE date,
    END_DATE date,
    CATEGORY_ID uuid,
    --
    primary key (ID)
);
