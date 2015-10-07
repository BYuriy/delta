-- $Id: 150203-userTimeZone.sql 19823 2015-02-05 14:55:52Z krivopustov $
-- add SEC_USER.TIME_ZONE and SEC_USER.TIME_ZONE_AUTO columns

alter table SEC_USER add TIME_ZONE varchar(50)^

alter table SEC_USER add TIME_ZONE_AUTO boolean^
