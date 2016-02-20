alter table DELTA_PRICE drop column CATEGORY_ID cascade ;
alter table DELTA_PRICE add column CATEGORY_ID varchar(36) ;
