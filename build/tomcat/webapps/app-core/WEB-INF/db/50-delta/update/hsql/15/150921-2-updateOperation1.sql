alter table DELTA_OPERATION add constraint FK_DELTA_OPERATION_STORAGE_DESTINATION_ID foreign key (STORAGE_DESTINATION_ID) references DELTA_STORAGE(ID);
create index IDX_DELTA_OPERATION_STORAGE_DESTINATION on DELTA_OPERATION (STORAGE_DESTINATION_ID);
