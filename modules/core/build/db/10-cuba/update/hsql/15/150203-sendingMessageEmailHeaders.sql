-- $Id: 150203-sendingMessageEmailHeaders.sql 19808 2015-02-04 11:12:06Z gorelov $
-- Add SYS_SENDING_MESSAGE.EMAIL_HEADERS

alter table SYS_SENDING_MESSAGE add EMAIL_HEADERS varchar(500)^