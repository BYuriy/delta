-- $Id: 150417-runtimePropertiesChanges.sql 21510 2015-05-28 14:27:23Z degtyarjov $
alter table SYS_ATTR_VALUE add column CODE varchar(100)^
alter table SYS_CATEGORY_ATTR add column TARGET_SCREENS varchar(4000)^

