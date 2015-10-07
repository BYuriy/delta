-- $Id: 150417-runtimePropertiesChanges.sql 21173 2015-04-23 11:51:15Z degtyarjov $
alter table SYS_ATTR_VALUE add column CODE varchar(100);
alter table SYS_CATEGORY_ATTR add column TARGET_SCREENS varchar(4000);

