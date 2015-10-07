alter table DELTA_CATEGORY drop column CATEGROY_ID cascade ;
alter table DELTA_CATEGORY add column CATEGROY_ID varchar(36) ;
