alter table DELTA_OPERATION add column CATEGORY_ID varchar(36) not null ;
alter table DELTA_OPERATION alter column CATEGORY_ID set not null ;
alter table DELTA_OPERATION drop column MATERIAL_ID cascade ;
