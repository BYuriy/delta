alter table DELTA_SUB_GROUP add constraint FK_DELTA_SUB_GROUP_GROUP_ID foreign key (GROUP_ID) references DELTA_GROUP(ID);
create index IDX_DELTA_SUB_GROUP_GROUP on DELTA_SUB_GROUP (GROUP_ID);
