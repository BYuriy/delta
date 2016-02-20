alter table DELTA_PRICE add constraint FK_DELTA_PRICE_CATEGORY_ID foreign key (CATEGORY_ID) references DELTA_CATEGORY(ID);
