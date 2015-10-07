-- $Id: 140716-changeSysFileSizeType.sql 17696 2014-07-16 13:33:48Z artamonov $
-- Description: Change type of column FILE_SIZE in SYS_FILE table

alter table SYS_FILE modify (FILE_SIZE number(19))^