-- begin DELTA_STORAGE
create table DELTA_STORAGE (
    ID uuid,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(100) not null,
    --
    primary key (ID)
)^
-- end DELTA_STORAGE
-- begin DELTA_CATEGORY
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
)^
-- end DELTA_CATEGORY
-- begin DELTA_STORAGE_CATEGORY_STATE
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
)^
-- end DELTA_STORAGE_CATEGORY_STATE
-- begin DELTA_OPERATION
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
)^
-- end DELTA_OPERATION
-- begin DELTA_GROUP
create table DELTA_GROUP (
    ID uuid,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(100) not null,
    PARENT_ID uuid,
    --
    primary key (ID)
)^
-- end DELTA_GROUP
-- begin DELTA_PRICE
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
)^
-- end DELTA_PRICE
