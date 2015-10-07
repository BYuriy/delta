-- $Id: 140708-addRememberMeTable.sql 17617 2014-07-09 06:42:47Z artamonov $
-- Description: add table for server-side remember me

create table SEC_REMEMBER_ME (
    ID varchar(36) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    --
    USER_ID varchar(36) not null,
    TOKEN varchar(32) not null,
    --
    primary key (ID),
    constraint FK_SEC_REMEMBER_ME_USER foreign key (USER_ID) references SEC_USER(ID)
)^