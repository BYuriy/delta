alter table DELTA_OPERATION add constraint FK_DELTA_OPERATION_STORAGE_ID foreign key (STORAGE_ID) references DELTA_STORAGE(ID);
alter table DELTA_OPERATION add constraint FK_DELTA_OPERATION_MATERIAL_ID foreign key (MATERIAL_ID) references DELTA_MATERIAL(ID);
create index IDX_DELTA_OPERATION_STORAGE on DELTA_OPERATION (STORAGE_ID);
create index IDX_DELTA_OPERATION_MATERIAL on DELTA_OPERATION (MATERIAL_ID);