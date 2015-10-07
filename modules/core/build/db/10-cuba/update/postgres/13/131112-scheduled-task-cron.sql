-- $Id: 131112-scheduled-task-cron.sql 14333 2013-11-13 12:27:03Z degtyarjov $ --
-- Add ability to use cron in scheduled tasks
alter table SYS_SCHEDULED_TASK  add column CRON varchar(100);
alter table SYS_SCHEDULED_TASK  add column SCHEDULING_TYPE varchar(1) default 'P';