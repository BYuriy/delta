alter table DELTA_CATEGORY add column GROUP_ID varchar(36) ;
alter table DELTA_CATEGORY drop column SUB_GROUP_ID cascade ;
