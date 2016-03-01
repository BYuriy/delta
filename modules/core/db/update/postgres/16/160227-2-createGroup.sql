alter table DELTA_GROUP add constraint FK_DELTA_GROUP_PARENT_ID foreign key (PARENT_ID) references DELTA_GROUP(ID);
create index IDX_DELTA_GROUP_PARENT on DELTA_GROUP (PARENT_ID);
