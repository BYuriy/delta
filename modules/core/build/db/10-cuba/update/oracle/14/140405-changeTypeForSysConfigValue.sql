-- $Id: 140405-changeTypeForSysConfigValue.sql 16605 2014-04-07 06:03:23Z artamonov $
-- Description: Increase max configuration parameter value length to unlimited

alter table SYS_CONFIG modify (VALUE clob) ^