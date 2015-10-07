alter table DELTA_STORAGE_MATERIAL_STATE add column CATEGORY_ID varchar(36) not null ;
alter table DELTA_STORAGE_MATERIAL_STATE alter column CATEGORY_ID set not null ;
alter table DELTA_STORAGE_MATERIAL_STATE drop column MATERIAL_ID cascade ;
