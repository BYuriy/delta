-- $Id: 140405-changeTypeForSysConfigValue.sql 16609 2014-04-07 06:24:07Z artamonov $
-- Description: Increase max configuration parameter value length to unlimited

alter table SYS_CONFIG alter column VALUE longvarchar^