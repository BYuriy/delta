alter table DELTA_CATEGORY add constraint FK_DELTA_CATEGORY_SUB_GROUP_ID foreign key (SUB_GROUP_ID) references DELTA_SUB_GROUP(ID);
create index IDX_DELTA_CATEGORY_SUB_GROUP on DELTA_CATEGORY (SUB_GROUP_ID);
