create table DELTA_CATEGORY (
    ID uuid,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(150) not null,
    GROUP_ID uuid,
    RELIAZIATION_PRICE decimal(19, 2),
    --
    primary key (ID)
);
