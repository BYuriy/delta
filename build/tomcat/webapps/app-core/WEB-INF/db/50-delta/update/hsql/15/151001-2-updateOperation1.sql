alter table DELTA_OPERATION add constraint FK_DELTA_OPERATION_CATEGORY_ID foreign key (CATEGORY_ID) references DELTA_CATEGORY(ID);
create index IDX_DELTA_OPERATION_CATEGORY on DELTA_OPERATION (CATEGORY_ID);
