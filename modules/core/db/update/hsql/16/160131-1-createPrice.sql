create table DELTA_PRICE (
    ID varchar(36) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CATEGORY_ID varchar(36),
    DATE_ timestamp,
    PRICE decimal(19, 2),
    --
    primary key (ID)
);
