-- $Id: 150630-entityClassToCategoryAttribute.sql 22173 2015-07-08 11:54:19Z degtyarjov $
alter table SYS_CATEGORY_ATTR add ENTITY_CLASS varchar2(500)^
update SYS_CATEGORY_ATTR set ENTITY_CLASS = DATA_TYPE where IS_ENTITY = 1^
update SYS_CATEGORY_ATTR set DATA_TYPE = 'ENTITY' where IS_ENTITY = 1^
--alter table SYS_CATEGORY_ATTR drop column IS_ENTITY^
