alter table DELTA_STORAGE_MATERIAL_STATE add constraint FK_DELTA_STORAGE_MATERIAL_STATE_CATEGORY_ID foreign key (CATEGORY_ID) references DELTA_CATEGORY(ID);
create index IDX_DELTA_STORAGE_MATERIAL_STATE_CATEGORY on DELTA_STORAGE_MATERIAL_STATE (CATEGORY_ID);
