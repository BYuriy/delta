-- $Id: 131108-sending-message-timeshift.sql 14248 2013-11-08 07:28:42Z budarov $ --
-- Make SendingMessage to be time shift friendly

alter table SYS_SENDING_MESSAGE alter column UPDATE_TS type timestamp with time zone;
alter table SYS_SENDING_MESSAGE alter column DEADLINE type timestamp with time zone;
