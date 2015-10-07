-- $Id: 141203-setNotNullSysFileName.sql 19043 2014-12-03 11:39:28Z artamonov $
-- Description: Set not null for NAME column of SYS_FILE

update SYS_FILE set NAME=ID where NAME is null^

alter table SYS_FILE modify NAME varchar2(500) not null^