alter table DELTA_CATEGORY add column SUBGROUP_ID varchar(36) ;
alter table DELTA_CATEGORY drop column CATEGROY_ID cascade ;
