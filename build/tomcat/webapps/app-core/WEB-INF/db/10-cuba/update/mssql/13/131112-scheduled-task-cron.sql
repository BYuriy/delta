-- $Id: 131112-scheduled-task-cron.sql 14355 2013-11-14 11:23:25Z hasanov $ --
-- Add ability to use cron in scheduled tasks
alter table SYS_SCHEDULED_TASK  add CRON varchar(100);
alter table SYS_SCHEDULED_TASK  add SCHEDULING_TYPE varchar(1) default 'P';