-- $Id: 130918-email-attachment-options.sql 12978 2013-09-18 07:51:35Z budarov $
-- Add columns to store email attachment disposition and encoding

alter table SYS_SENDING_ATTACHMENT add VERSION integer;
alter table SYS_SENDING_ATTACHMENT add UPDATE_TS datetime;
alter table SYS_SENDING_ATTACHMENT add UPDATED_BY varchar(50);
alter table SYS_SENDING_ATTACHMENT add DELETE_TS datetime;
alter table SYS_SENDING_ATTACHMENT add DELETED_BY varchar(50);
alter table SYS_SENDING_ATTACHMENT add DISPOSITION varchar(50);
alter table SYS_SENDING_ATTACHMENT add TEXT_ENCODING varchar(50);
