alter table DELTA_CATEGORY add column SUB_GROUP_ID varchar(36) ;
alter table DELTA_CATEGORY drop column SUBGROUP_ID cascade ;
