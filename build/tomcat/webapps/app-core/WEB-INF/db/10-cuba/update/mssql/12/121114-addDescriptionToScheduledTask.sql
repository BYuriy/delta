-- $Id: 121114-addDescriptionToScheduledTask.sql 13792 2013-10-17 11:10:31Z artamonov $

alter table SYS_SCHEDULED_TASK add DESCRIPTION varchar(1000);