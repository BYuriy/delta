alter table DELTA_PRICE add column START_DATE timestamp ;
alter table DELTA_PRICE add column END_DATE timestamp ;
alter table DELTA_PRICE drop column DATE_ cascade ;
