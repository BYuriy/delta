alter table DELTA_OPERATION add column DESCRIPTION varchar(150) ;
alter table DELTA_OPERATION drop column AVERAGE_PRICE cascade ;
