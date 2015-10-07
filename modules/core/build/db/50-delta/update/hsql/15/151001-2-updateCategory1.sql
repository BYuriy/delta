alter table DELTA_CATEGORY add constraint FK_DELTA_CATEGORY_CATEGROY_ID foreign key (CATEGROY_ID) references DELTA_SUB_GROUP(ID);
