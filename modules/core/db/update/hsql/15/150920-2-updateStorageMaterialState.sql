alter table DELTA_STORAGE_MATERIAL_STATE add column DATE_TIME timestamp ;
alter table DELTA_STORAGE_MATERIAL_STATE drop column AVERAGE_PRICE cascade ;
